package org.rsa;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by dinghy on 2017/8/30.
 */
public class RsaUtils {

    // ǩ������
    private Signature sign;
    private static final RsaUtils rsaHelper = new RsaUtils();

    private String pubkey;
    private String prikey_pkcs8;
    private String prikey_openssl;

    private RsaUtils() {
        try {
            sign = Signature.getInstance("SHA1withRSA");
        } catch (NoSuchAlgorithmException nsa) {
            System.out.println("" + nsa.getMessage());
        }
    }

    public static RsaUtils getInstance() {
        return rsaHelper;
    }

    private PrivateKey getPrivateKey(String privateKeyStr) {
        try {
            byte[] privateKeyBytes = b64decode(privateKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                    privateKeyBytes);
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException e) {
            System.out.println("Invalid Key Specs. Not valid Key files."
                    + e.getCause());
            return null;
        } catch (NoSuchAlgorithmException e) {
            System.out
                    .println("There is no such algorithm. Please check the JDK ver."
                            + e.getCause());
            return null;
        }
    }

    private PublicKey getPublicKey(String publicKeyStr) {
        try {
            byte[] publicKeyBytes = b64decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                    publicKeyBytes);
            return keyFactory.generatePublic(publicKeySpec);

        } catch (InvalidKeySpecException e) {
            System.out.println("Invalid Key Specs. Not valid Key files."
                    + e.getCause());
            return null;
        } catch (NoSuchAlgorithmException e) {
            System.out
                    .println("There is no such algorithm. Please check the JDK ver."
                            + e.getCause());
            return null;
        }
    }

    /**
     * RSA ����ǩ��
     *
     * @param toBeSigned
     *            (��ǩ����ԭ��)
     * @param priKey
     *            (RSA˽Կ��
     * @return ������RSAǩ���������ǩ������base64���룩
     */
    public String signData(String toBeSigned, String priKey) {

        try {
            PrivateKey privateKey = getPrivateKey(priKey);
            byte[] signByte = toBeSigned.getBytes("utf-8");
            Signature rsa = Signature.getInstance("SHA1withRSA");
            rsa.initSign(privateKey);
            rsa.update(signByte);
            return b64encode(rsa.sign());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException in) {
            System.out
                    .println("Invalid Key file.Please check the key file path"
                            + in.getCause());
        } catch (Exception se) {
            System.out.println(se);
        }
        return null;
    }

    /**
     * RSA ����ǩ����֤
     *
     * @param signature
     *            ��RSAǩ�����ݣ�base64���룩
     * @param data
     *            ������֤������ԭ�ģ�
     * @param pubKey
     *            ��RSA��Կ���ݣ�
     * @return ������֤�����TRUE:��֤�ɹ���FALSE:��֤ʧ�ܣ�
     */
    public boolean verifySignature(String signature, String data, String pubKey) {
        try {
            byte[] signByte = b64decode(signature);
            byte[] dataByte = data.getBytes("utf-8");
            PublicKey publicKey = getPublicKey(pubKey);
            sign.initVerify(publicKey);
            sign.update(dataByte);
            return sign.verify(signByte);
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * base64����
     *
     * @param data
     * @return
     */
    private String b64encode(byte[] data) {
        return new BASE64Encoder().encode(data);
    }

    /**
     * base64����
     *
     * @param data
     * @return
     */
    private byte[] b64decode(String data) {
        try {
            return new BASE64Decoder().decodeBuffer(data);
        } catch (Exception ex) {
        }
        return null;
    }



    public static void main(String[] args){

        String[] params = args[0].split(",");

        String type = params[0];

        if(type.equals("0")){

            String privateKey = params[1];
            String toBeSigned = params[2];

            String sign = rsaHelper.signData(toBeSigned , privateKey);
            System.out.println(sign);

        }else if(type.equals("1")){
            String publicKey = params[1];
            String signature = params[2];
            String date = params[3];

            boolean sign = rsaHelper.verifySignature(signature , date , publicKey);

            System.out.println(sign);

        }


    }

}
