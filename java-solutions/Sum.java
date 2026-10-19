//  public class Sum {
//    public static void main(String[] args){
//        //System.out.println(args[0]);
//        int[] numbers = new int[args.length];
//        for (int i = 0; i<args.length ; i++ ){
//           numbers[i] = Integer.parseInt(args[i]);
//             //System.out.println(args[i]);
//         }
//         int sum = 0;
//        for (int g = 0; g<args.length; g++){
//           sum+=numbers[g];
//        }
//         System.out.println("Результат: "+sum);
//     }
// }

//NOTE: StringBuilder
//StringBuilder sb = new StringBuilder();

// public class Sum{
//    public static void main(String[] args){
//        int summa = 0;
//        for(int i = 0; i<args.length ;i++){
//            String str = args[i];
//             for(int j = 0; j<str.length() ; j++ ){
//                char letter = str.charAt(j);
//                if (Character.isDigit(letter)){
//                    int num = Character.getNumericValue(letter);
//                    summa+= num;
//                }

//            }

//        }
//     System.out.println("Результат: "+summa);
//    }
// }

// java Sum " +123 -456 " - args[0] -> "+123 -456"  str.length = 9, поэтому строгое неравенство всё делает чётко
// "789 +0 -999" a - args[1]
// " -42 +1000 "  - args[2]
// public class Sum{
//     public static void main(String[] args){
//         int summa = 0;
//         StringBuilder number = new StringBuilder();
//         for (int i = 0 ; i<args.length ; i++ ){
//             number.setLength(0);
//             // StringBuilder number = new StringBuilder();
//             String str = args[i];
//             for (int j = 0 ; j<str.length(); j++ ){
//                 if (Character.isWhitespace(str.charAt(j))){
//                     if (number.length()>0) { 
//                 summa+= Integer.parseInt(number.toString()); // toString нужен для конвертации SB -> String
//                 number.setLength(0);
//                      }
//                 }
//                 else{
//                     number.append(str.charAt(j));
//                 }
//             }
//         if (number.length()>0){
//             summa+= Integer.parseInt(number.toString());
//             }
//         }
//     System.out.println(summa);
//     }    
// }

// public class Sum {
//     public static void main(String[] args) {
//         int sum = 0;

//         // Собираем все аргументы в одну строку (без удаления внутренних символов)
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < args.length; i++) {
//             if (args[i] != null && args[i].length() > 0) {
//                 if (sb.length() > 0) sb.append(' ');
//                 sb.append(args[i]);
//             }
//         }

//         // Ищем все целые числа, допускаем знак + или -
//         java.util.regex.Pattern p = java.util.regex.Pattern.compile("[+-]?\\d+");
//         java.util.regex.Matcher m = p.matcher(sb.toString());

//         while (m.find()) {
//             String token = m.group();
//             sum += Integer.parseInt(token); // по условию int достаточно
//         }

//         System.out.println(sum);
//     }
// }
public class Sum {
    public static void main(String[] args) {
        int summa = 0;
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
                        summa += Integer.parseInt(str.substring(j - counter, j));
                        counter = 0;
                    }
                } else {
                    counter++;
                }

            }
            if (counter > 0) {
                summa += Integer.parseInt(str.substring(str.length() - counter, str.length()));
            }

        }
        System.out.println(summa);
    }

}
