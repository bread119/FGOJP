package cn.mcfun.utils;

import cn.mcfun.Main;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import java.util.Base64;

public class AES {
    public static String encryptWithAesCBC(String plaintext) {
        try {
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new PKCS7Padding());
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(Main.key), Main.iv);
            cipher.init(true, ivAndKey);
            return new String(Base64.getEncoder().encode(cipherData(cipher, plaintext.getBytes())));
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptWithAesCBC(String encrypted) {
        try {
            byte[] cipherText = Base64.getDecoder().decode(encrypted);
            PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new PKCS7Padding());
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter("o~`Ipti~uGLEb|44weQ5cMlPn]GqK@5N".getBytes()), "{|y|a{P{OQyZ^kyz@Zqlb;c>~BKq[};o".getBytes());
            aes.init(false, ivAndKey);
            return new String(cipherData(aes, cipherText));
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) throws InvalidCipherTextException {
        int minSize = cipher.getOutputSize(data.length);
        byte[] outBuf = new byte[minSize];
        int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
        int length2 = cipher.doFinal(outBuf, length1);
        int actualLength = length1 + length2;
        byte[] cipherArray = new byte[actualLength];
        for (int x = 0; x < actualLength; x++) {
            cipherArray[x] = outBuf[x];
        }
        return cipherArray;
    }

}
