public class SumDouble {
    public static void main(String[] args) {
        double summa = 0;
        // System.err.println("start");
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            // System.err.println(str);
            int counter = 0;
            for (int j = 0; j < str.length(); j++) {
                // System.err.println(str.charAt(j));
                if (Character.isWhitespace(str.charAt(j))) {
                    if (counter != 0) {
                        // System.err.println(str.substring(j-counter,j));
                        summa += Double.parseDouble(str.substring(j - counter, j));
                        counter = 0;
                    }
                } else {
                    counter++;
                }

            }
            if (counter > 0) {
                summa += Double.parseDouble(str.substring(str.length() - counter, str.length()));
            }

        }
        System.out.println(summa);
    }

}

// public class SumDouble{
// public static void main(String[] args){
// Double summa = 0.0;
// StringBuilder number = new StringBuilder();
// for (int i = 0 ; i<args.length ; i++ ){
// number.setLength(0);
// String str = args[i];
// for (int j = 0 ; j<str.length(); j++ ){
// if (Character.isWhitespace(str.charAt(j))){
// if (number.length()>0) {
// // summa+= Double.parseDouble(number.toString());
// summa+= Double.parseDouble(number.substring(0,number.length()));
// number.setLength(0);
// }
// }
// else{
// number.append(str.charAt(j));
// }
// }
// if (number.length()>0){
// summa+= Double.parseDouble(number.toString());
// }
// }
// System.out.println(summa);
// }
// }
