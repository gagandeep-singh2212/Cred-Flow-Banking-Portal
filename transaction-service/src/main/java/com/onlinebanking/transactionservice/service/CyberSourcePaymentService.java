package com.onlinebanking.transactionservice.service;

import Api.PaymentsApi;
import Invokers.ApiClient;
import Invokers.ApiException;
import Model.*;
import com.cybersource.authsdk.core.MerchantConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Properties;


@Service
public class CyberSourcePaymentService {
    private static String responseCode = null;
    private static String status = null;
    private static Properties merchantProp;
    public static boolean userCapture = true;

    public static void WriteLogAudit(int status) {
        String filename = MethodHandles.lookup().lookupClass().getSimpleName();
        System.out.println("[Sample Code Testing] [" + filename + "] " + status);
    }

    public String makePayment(double amount) {
        CreatePaymentRequest requestObj = new CreatePaymentRequest();

        Ptsv2paymentsClientReferenceInformation clientReferenceInformation = new Ptsv2paymentsClientReferenceInformation();
        clientReferenceInformation.code("TC50171_3");
        requestObj.clientReferenceInformation(clientReferenceInformation);

        Ptsv2paymentsProcessingInformation processingInformation = new Ptsv2paymentsProcessingInformation();
        processingInformation.capture(false);
        if (userCapture) {
            processingInformation.capture(true);
        }

        requestObj.processingInformation(processingInformation);

        Ptsv2paymentsPaymentInformation paymentInformation = new Ptsv2paymentsPaymentInformation();
        Ptsv2paymentsPaymentInformationCard paymentInformationCard = new Ptsv2paymentsPaymentInformationCard();
        paymentInformationCard.number("4111111111111111");
        paymentInformationCard.expirationMonth("12");
        paymentInformationCard.expirationYear("2031");
        paymentInformation.card(paymentInformationCard);

        requestObj.paymentInformation(paymentInformation);

        Ptsv2paymentsOrderInformation orderInformation = new Ptsv2paymentsOrderInformation();
        Ptsv2paymentsOrderInformationAmountDetails orderInformationAmountDetails = new Ptsv2paymentsOrderInformationAmountDetails();
        orderInformationAmountDetails.totalAmount(String.valueOf(amount));
        orderInformationAmountDetails.currency("USD");
        orderInformation.amountDetails(orderInformationAmountDetails);

        Ptsv2paymentsOrderInformationBillTo orderInformationBillTo = new Ptsv2paymentsOrderInformationBillTo();
        orderInformationBillTo.firstName("John");
        orderInformationBillTo.lastName("Doe");
        orderInformationBillTo.address1("1 Market St");
        orderInformationBillTo.locality("san francisco");
        orderInformationBillTo.administrativeArea("CA");
        orderInformationBillTo.postalCode("94105");
        orderInformationBillTo.country("US");
        orderInformationBillTo.email("test@cybs.com");
        orderInformationBillTo.phoneNumber("4158880000");
        orderInformation.billTo(orderInformationBillTo);

        requestObj.orderInformation(orderInformation);

        PtsV2PaymentsPost201Response result = null;
        try {

            merchantProp = getMerchantDetails();
            ApiClient apiClient = new ApiClient();
            MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
            apiClient.merchantConfig = merchantConfig;

            PaymentsApi apiInstance = new PaymentsApi(apiClient);
            result = apiInstance.createPayment(requestObj);

            responseCode = apiClient.responseCode;
            status = apiClient.status;
            System.out.println("ResponseCode :" + responseCode);
            System.out.println("ResponseMessage :" + status);
            System.out.println(result);
            WriteLogAudit(Integer.parseInt(responseCode));

        } catch (ApiException e) {
            e.printStackTrace();
            WriteLogAudit(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }


    public static Properties getMerchantDetails() {
        Properties props = new Properties();

        // HTTP_Signature = http_signature and JWT = jwt
        props.setProperty("authenticationType", "http_signature");
        props.setProperty("merchantID", "my_dream2");
        props.setProperty("runEnvironment", "apitest.cybersource.com");
        props.setProperty("requestJsonPath", "src/main/resources/request.json");

        // MetaKey Parameters
        props.setProperty("portfolioID", "");
        props.setProperty("useMetaKey", "false");

        // JWT Parameters
        props.setProperty("keyAlias", "testrest");
        props.setProperty("keyPass", "testrest");
        props.setProperty("keyFileName", "testrest");

        // P12 key path. Enter the folder path where the .p12 file is located.

        props.setProperty("keysDirectory", "src/main/resources");
        // HTTP Parameters
        props.setProperty("merchantKeyId", "7ad536ce-492f-494c-ace4-c81a2fd74126");
        props.setProperty("merchantsecretKey", "vEMvoiaA3tK3m5MVxzCYU/HZiYp5/WTn5uIkmIyvGhw=");
        // Logging to be enabled or not.
        props.setProperty("enableLog", "true");
        // Log directory Path
        props.setProperty("logDirectory", "log");
        props.setProperty("logFilename", "cybs");

        // Log file size in KB
        props.setProperty("logMaximumSize", "5M");

        // OAuth related properties.
        props.setProperty("enableClientCert", "false");
        props.setProperty("clientCertDirectory", "src/main/resources");
        props.setProperty("clientCertFile", "");
        props.setProperty("clientCertPassword", "");
        props.setProperty("clientId", "");
        props.setProperty("clientSecret", "");

		/*
		PEM Key file path for decoding JWE Response Enter the folder path where the .pem file is located.
		It is optional property, require adding only during JWE decryption.
		*/
        props.setProperty("jwePEMFileDirectory", "src/main/resources/NetworkTokenCert.pem");

        //Add the property if required to override the cybs default developerId in all request body
        props.setProperty("defaultDeveloperId", "");

        return props;

    }
}
