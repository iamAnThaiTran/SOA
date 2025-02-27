package org.example;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientUbuntu extends AbstractJavaSamplerClient {

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.sampleStart(); //

        try {
            // Kết nối tới RMI registry
            Registry registry = LocateRegistry.getRegistry("192.168.99.26", 1099);
            OrderService service = (OrderService) registry.lookup("OrderService");

            // Gửi request
            String productId = "Product_5";
            int quantity = 5;
            double total = service.calculateTotal(productId, quantity);

            result.setResponseData(("Total cost: " + total).getBytes());
            result.setSuccessful(true);
            result.setResponseMessage("Success");
            result.setResponseCode("200");
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setResponseMessage(e.getMessage());
            result.setResponseCode("500");
        } finally {
            result.sampleEnd(); //
        }

        return result;
    }
}

