package com.giang.Slytherin.utils;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TextUtils {
    public static String HEADER_AUTHOR = "SYSTEM_OTP_1";
    public static String HEADER_AUTHOR_0 = "SYSTEM_OTP_0";
    private static String a = "ĂẮẶẰÂẤẦẬăắằặâấầậÁÀẠáàạẲẴẳẵẨẪẩẫẢÃảã";
    private static String e = "éèẹÉÈẸÊẾỀỆêếềệẺẼẻẽỂỄểễ";
    private static String i = "íìịÍÌỊỈĨỉĩ";
    private static String o = "ÓÒỌóòọÔỐỒỘôốồộƠỚỜỢơớờợỎÕỏõỔỖổỗỞỖởỡ";
    private static String u = "ÚÙỤúùụƯỨỪỰưứừ" +
            "ựỦŨủũỬỮửữ";
    private static String y = "ÝỲỴýỳỵỶỸỷỹ";
    private static String d = "Đđ";
    public static final String EMPTY = "";
    public static final String PHONE_REGEX = "^(05|03|04|07|08|01[2689]|09|024|028)[0-9]{8}$|(021[012345689]|023[23456789]|020[3456789]|022[0123456789]|029[01234679]|025[123456789]|026[01239]|027[01234567])[0-9]{7}$";

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String join(String str, List<String> addresses) {
        if (str == null) str = "";
        if (addresses == null || addresses.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < addresses.size(); i++) {
            if (i < addresses.size() - 1) {
                stringBuilder.append(addresses.get(i) + str);
                continue;
            }
            stringBuilder.append(addresses.get(i));
        }
        return stringBuilder.toString();
    }

    public static String validateText(String s) {
        if (s == null || s.trim().isEmpty()) return "";
        return s;
    }

    public static String hidePhoneNumber(String originalPhone) {
        if (TextUtils.isEmpty(originalPhone)) return "";
        return originalPhone.replaceAll("\\b(\\d{3})\\d+(\\d{3})", "$1*****$2");
    }

    public static String hidePhoneNumber3Dot(String originalPhone) {
        if (TextUtils.isEmpty(originalPhone)) return "";
        return originalPhone.replaceAll("\\b(\\d{2})\\d+(\\d{2})", "$1***$2");
    }

    public static String hideText(String originalText) {
        if (TextUtils.isEmpty(originalText)) return "";
        return originalText.replaceAll("(.{5}).{0,}", "$1*** *** ***");
    }

    public static String formatVnCurrency(String price) {

        NumberFormat format =
                new DecimalFormat("#,##0.00");// #,##0.00 ¤ (¤:// Currency symbol)
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale

        price = (!TextUtils.isEmpty(price)) ? price : "0";
        price = price.trim();
        price = format.format(Double.parseDouble(price));
        price = price.replaceAll(",", "\\.");

        if (price.endsWith(".00")) {
            int centsIndex = price.lastIndexOf(".00");
            if (centsIndex != -1) {
                price = price.substring(0, centsIndex);
            }
        }
        price = String.format("%s đ", price);
        return price;
    }

    public static String formatVnExcel(String price) {
        NumberFormat format =
                new DecimalFormat("#,##0.00");// #,##0.00 ¤ (¤:// Currency symbol)
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale

        price = (!TextUtils.isEmpty(price)) ? price : "0";
        price = price.trim();
        price = format.format(Double.parseDouble(price));

        if (price.endsWith(".00")) {
            int centsIndex = price.lastIndexOf(".00");
            if (centsIndex != -1) {
                price = price.substring(0, centsIndex);
            }
        }
        return price;
    }

    public static char convertCharUTF8(char ch) {
        String tmp = "" + ch;
        if (a.contains(tmp)) return 'A';
        else if (e.contains(tmp)) return 'E';
        else if (i.contains(tmp)) return 'I';
        else if (o.contains(tmp)) return 'O';
        else if (u.contains(tmp)) return 'U';
        else if (y.contains(tmp)) return 'Y';
        else if (d.contains(tmp)) return 'D';
        else return Character.toUpperCase(ch);
    }

    public static String convertStringUTF8(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isLetter(str.charAt(i)))
                    sb.append(convertCharUTF8(str.charAt(i)));
                else
                    sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String get2Words(String txt) {
        if (txt == null) return "";
        StringBuffer s = new StringBuffer("");
        StringTokenizer stringTokenizer = new StringTokenizer(txt);
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            s.append(stringTokenizer.nextToken() + " ");
            i++;
            if (i >= 2) break;
        }
        return s.toString().trim() + "...";
    }

    public static String getLastWords(String txt) {
        if (txt == null) return "";
        String s = "";
        StringTokenizer stringTokenizer = new StringTokenizer(txt);
        while (stringTokenizer.hasMoreTokens()) {
            s = stringTokenizer.nextToken();
        }
        return s;
    }

    public static String randomCaptcha(int length) {
        String str = "abcde2345678gfynmnpwx";
        SecureRandom rand = new SecureRandom();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(str.charAt(rand.nextInt(str.length())));
        }
        return text.toString();
    }

    public static String randomCaptchaNumber(int length) {
        String str = "0123456789";
        SecureRandom rand = new SecureRandom();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(str.charAt(rand.nextInt(str.length())));
        }
        return text.toString();
    }

    public static String compress(final String str) throws IOException {
        if ((str == null) || (str.length() == 0)) {
            return null;
        }
        ByteArrayOutputStream obj = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.flush();
        gzip.close();
        return Base64.getEncoder().encodeToString(obj.toByteArray());
    }

    public static String decompress(final String compressedd) throws IOException {
        byte[] compressed = Base64.getDecoder().decode(compressedd);
        final StringBuilder outStr = new StringBuilder();
        if ((compressed == null) || (compressed.length == 0)) {
            return "";
        }
        if (isCompressed(compressed)) {
            final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outStr.append(line);
            }
        } else {
            outStr.append(compressed);
        }
        return outStr.toString();
    }

    public static boolean isCompressed(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }


    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean isValidEmail(String target) {
        return (!isEmpty(target) && EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (TextUtils.isEmpty(phone)) return false;
        if (phone.length() < 7) return false;
        if (phone.startsWith("0084")) {
            phone = "0" + phone.substring(4);
        } else if (phone.startsWith("84")) {
            phone = "0" + phone.substring(2);
        } else if (phone.startsWith("+84")) {
            phone = "0" + phone.substring(3);
        }
        if (!phone.startsWith("0")) return false;
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    public static boolean isValidPhoneNumberV2(String phone) {
        if (TextUtils.isEmpty(phone)) return false;
        if (phone.length() < 7) return false;
        if (phone.startsWith("0084")) {
            return false;
        }
        if (phone.startsWith("84")) {
            return false;
        }
        if (phone.startsWith("+84")) {
            return false;
        }
        if (!phone.startsWith("0")) return false;
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    public static boolean isNullOrZero(BigDecimal value) {
        return (value == null || value.compareTo(BigDecimal.ZERO) == 0);
    }

    public static String upperFirstChar(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toUpperCase(Locale.ENGLISH) + input.substring(1);
    }

    public static String lowerFirstChar(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toLowerCase(Locale.ENGLISH) + input.substring(1);
    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        Long result = defaultValue;
        if (obj1 != null && !obj1.equals("")) {
            if (obj1 instanceof BigDecimal) {
                return ((BigDecimal) obj1).longValue();
            }
            if (obj1 instanceof BigInteger) {
                return ((BigInteger) obj1).longValue();
            }
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    /**
     * @param obj1 Object
     * @return Long
     */
    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, 0L);
    }

    public static Double safeToDouble(Object obj1, Double defaultValue) {
        Double result = defaultValue;
        if (obj1 != null) {
            try {
                result = Double.parseDouble(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    public static Double safeToDouble(Object obj1) {
        return safeToDouble(obj1, 0.0);
    }

    public static Short safeToShort(Object obj1, Short defaultValue) {
        Short result = defaultValue;
        if (obj1 != null) {
            try {
                result = Short.parseShort(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    public static Short safeToShort(Object obj1) {
        return safeToShort(obj1, (short) 0);
    }

    /**
     * @param obj1
     * @param defaultValue
     * @return
     * @author phuvk
     */
    public static int safeToInt(Object obj1, int defaultValue) {
        int result = defaultValue;
        if (obj1 != null) {
            try {
                result = Integer.parseInt(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    /**
     * @param obj1 Object
     * @return int
     */
    public static int safeToInt(Object obj1) {
        return safeToInt(obj1, 0);
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }

        String result = obj1.toString();
        if (result != null) {
            result = result.replaceAll("\\p{Cc}", "?");
        }
        return result;
    }


    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }

    public static boolean safeEqual(Long obj1, Long obj2) {
        if (obj1.equals(obj2)) return true;
        return obj2 != null && obj1.compareTo(obj2) == 0;
    }

    public static boolean safeEqual(BigInteger obj1, BigInteger obj2) {
        if (obj1 == obj2) return true;
        return (obj1 != null) && obj1.equals(obj2);
    }

    public static boolean safeEqual(Short obj1, Short obj2) {
        if (obj1 == obj2) return true;
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    public static boolean safeEqual(String obj1, String obj2) {
        if (obj1 == obj2) return true;
        return ((obj1 != null) && obj1.equals(obj2));
    }

    public static boolean isNullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(final Object[] collection) {
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNumeric(String string) {
        if (string != null && string.length() != 0) {
            int l = string.length();

            for (int i = 0; i < l; ++i) {
                if (!Character.isDigit(string.codePointAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static String getCorrectPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return phone;
        }
        if (phone.startsWith("0084")) {
            return "0" + phone.substring(4);
        }
        if (phone.startsWith("+84")) {
            return "0" + phone.substring(3);
        }
        if (phone.startsWith("84")) {
            return "0" + phone.substring(2);
        }
        return phone;
    }

    public static String convertStringToNoSpace(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.replaceAll(" ", "_");
    }

    public static String md5(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        byte[] md5 = md.digest(s.getBytes());
        md5 = md.digest(md5);
        return new String(md5, StandardCharsets.UTF_8);
    }
}
