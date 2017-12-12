package com.springapp.mvc;


import org.utils.RsaUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinghy on 2017/8/21.
 */
public class TestHttpClient {

    private String url = "http://localhost:8881/123";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public TestHttpClient(){
        httpClientUtil = new HttpClientUtil();
    }

    public void test(){
        String http = url;
        Map<String,String> createMap = new HashMap<String, String>();
        createMap.put("v","∂°¥Ûœ¿");
        createMap.put("authpass","*****");
        createMap.put("key","123");
        createMap.put("orgname","****");
        String httpOrgCreateTestRtn = httpClientUtil.doPost(http,createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);
        if(httpOrgCreateTestRtn.equals("ok")){
            System.out.println("≤‹ƒ·¬Í");
        }
    }

    public static void main(String[] args){
//        TestHttpClient test = new TestHttpClient();
//        test.test();

        RsaUtils rsaUtils = RsaUtils.getInstance();
        String sign = "transId=1001161510286035&accountNumber=43769&cardNo=60138220000644697933&amount=0.8";
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIhohOauOc1vbfUOw1uIIcH+HWFY1P++fQlb/b" +
                "OgoUqzt/vZNCm8gqRM8VVG0ZJZpKFK2kguVa42pbJfOunHLjYnN7GyMaPDc+CczX4l5gz7dc/uD1" +
                "sL6CxmL9TMkchS8FY2KJkwZ9cG6b38LgqsT4UUGuxxmDQQMI3SUZoEbJCwIDAQAB";

//        String data = "RKyz+pHJmMxF7b1XV8FbL8JG9Xy69p0WY3p/gvR5a3EgTftX82iNTg1g17h+LuyIYAL5I9AXRePNY7MjWxM6aRlnMtvl/bEmmuKest6PKj4VOwKc/JruL0dm2n6njPa3Cf3+la3D6GPR1Z4mPzuTEftqAVHCZ3VI+/To45rbMLU=";
        String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIiGiE5q45zW9t9Q7DW4ghwf4dYV" +
                "jU/759CVv9s6ChSrO3+9k0KbyCpEzxVUbRklmkoUraSC5Vrjalsl866ccuNic3sbIxo8Nz4JzNfi" +
                "XmDPt1z+4PWwvoLGYv1MyRyFLwVjYomTBn1wbpvfwuCqxPhRQa7HGYNBAwjdJRmgRskLAgMBAAEC" +
                "gYBCR02VBD9LvPBMqbSCv/gqPzAcBop78gcwNALYIu0R668O7LG4/HfITW9bI4lzU9X7cN57pzBB" +
                "V3YimeIbOshq8vQbElI89fCw3k5/E2MjOnPOZC8kJpVw95HWJ39W/ByH9PgFAVjWjvrooghjbZLE" +
                "da8oWgP5purfXxV2u9OOgQJBAN5wUvRs3ewzY+vni4/jDlEg7iaumJGeBXT5x99oMMXIcXDgwd+i" +
                "BJyXMz5MSz8jWLh2o7sXxStG0idHkmtJ72kCQQCdH9FDory6wnYodR8/eYfw6q/LRzeMOLJBGk8q" +
                "Ick3rh/wtGxptL4G4AmUpTVEkrzRN3VQAt8l2w+OwPci4ZpTAkBugiFEW525TiKzPXqBho1DUvfd" +
                "8dnMZlRRtBJPdGTereFl8dN/te9SmG+V4LN1b6sRIafgOEP1Zx7a8tzUPowZAkAs/ShBEn6xhu6o" +
                "JOj+lsC61afiL5ETIETB6VOTf7sKq1vP4wlLtwnWFB4KJse6y70uto9rinb0cuXgfRYGMcyrAkEA" +
                "xLsWOyHG7pm7xax02Wqbj5pTaupTj7k9KEBcqxzXGg01yKwGD168ISPHT/w6We6RAUu+SqxpScmh" +
                "YQOOyv8TmQ==";
        String type = rsaUtils.signData(sign, pri);
//
//        System.out.println(rsaUtils.signData(sign,"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJRKhl8KtFts361jV8e5YKct0+Nw" +
//                "jc6vmfY+1ZKPC75fwf87V4WaemHxAtFCftw/alV9vsnTTN5GiGyv2oPXNWWbP03UgQ5zG064aObH" +
//                "c0pINCdH8D8d/SXM9YfjAH6O7t1OxZP2FBYouwZ7lJZXQ6fCmYOYABVET95jFk+2XJ55AgMBAAEC" +
//                "gYBI1+bzzu1Tr8KciJ05Fd3doYxhQAvYyLfHl4wQB0aMiLtiJgNTNNQDQoHQy2pHxLr2LePHfo1W" +
//                "7qzbPvMHKnMmp+m2eXFIHVgM1ejPnyabvTsjI4Blu58kVjGvLyohbH2OvIRI/AFXR9Mz/Hc7ATv0" +
//                "vHaBQgvq/04/esBmJMrzZQJBAO54f0ea2zqlwKqVm/Tsftcmvit4wgvw6O3tVAOgvoLSHkRJw+a4" +
//                "j5IuZ0QDkS+FrTct/9wglY3Q6lfB3ZPS4ssCQQCfMQsCLYaDX8HGPnEYiNLGUKyIDbd0R8RfDT/J" +
//                "MXsEx9k15MaSJmt0U+znNZuSg7LMSGiW1L9T0S3Y/pKV7edLAkEA3/d9mtOe2Gr6E3wlmBdRXWI+" +
//                "svdcT/i321XNVQbwRk9vK7WX7qYh+SnpxKARCG/k6fEi3ywfKa0vmrIyF3a1lwJATJSELjUGW5aq" +
//                "GhsZvuq7MqnGheDLWwXXQr6V68yA2IjnRhTbLZ2L3bct5QAV6gKu9bTzk3Oe4sxjGNtGWxfKRwJB" +
//                "AKNZranFoQQpus85IcKtqMc7RZH9SEyIhheXAl2baOxnnOuME+TFtC2XcvCGeNJ6LHU08S0e3yEC" +
//                "tJiKYXu398k="));

        System.out.println(rsaUtils.verifySignature(type , sign , pub));
        System.out.println(pub.length()+":∞°∞°∞°"+pri.length());
    }

}
