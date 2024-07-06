package org.pepe.theory.java17;

import java.util.LinkedList;
import java.util.Queue;

public class SwitchExpressionExample {
    public static void main(String[] args) {

        fallThrough();
    }

    private static void basicSyntax() {
        String dayString = "Monday";

        int day = switch (dayString) {
            case "Monday" -> 1;
            case "Tuesday" -> 2;
            case "Wednesday" -> 3;
            default -> -1;
        };

    }

    public static void codeBlock() {
        String dayString = "Monday";

        int day = switch (dayString) {
            case "Monday" -> {
                System.out.println("First day of the week");
                yield 1;
            }
            case "Tuesday" -> {
                System.out.println("Second day of the week");
                yield 2;
            }
            default -> {
                System.out.println("Unknown day !");
                yield -1;
            }
        };

        int day2 = switch (dayString) {
            case "Monday" : {
                System.out.println("First day of the week");
                yield 1;
            }
            case "Tuesday" : {
                System.out.println("Second day of the week");
                yield 2;
            }
            default : {
                System.out.println("Unknown day !");
                yield -1;
            }
        };
    }

    public static void fallThrough() {
        String dayString = "Sunday";
        int day = switch (dayString) {
            case "Saturday", "Sunday" -> {
                System.out.println("Weekend is here!");
                yield 1;
            }
            default -> {
                System.out.println("Get back to work!");
                yield -1;
            }
        };
        System.out.println(day);
        String dayString2 = "Sunday";


        switch (dayString) {
            case "Saturday", "Sunday":
                System.out.println("Weekend is here!");
                break;
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday":
                System.out.println("Still work day.");
                break;
        }


    }

    public int firstUniqChar(String s) {
        int[] counts = new int[33];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = ++counts[c - 'a'];
            if (count == 1) q.offer(i);
            else if (!q.isEmpty() && c == s.charAt(q.peek())) {
                while (!q.isEmpty() && counts[s.charAt(q.peek()) - 'a'] > 1) q.poll();
            }
        }
        return q.isEmpty() ? -1 : q.poll();
    }

    public static void exception() {
        String dayString = "Sunday";
        boolean weekend = switch (dayString) {
            case "Saturday", "Sunday" -> {
                System.out.println("Weekend is here!");
                yield true;
            }
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> false;
            default -> {
                throw new RuntimeException("I don't know what you mean");
            }
        };
        System.out.println(weekend);
    }

    public static void scope() {
        String dayString = "Saturday";
//        switch (dayString) {
//            case "Saturday":
//            case "Sunday": {
//                String message = "Weekend is here!";
//                System.out.println(message);
//                break;
//            }
//            case "Monday": {
//                String message = "Weekend is not here!";
//                System.out.println(message);
//                break;
//            }
//            default: {
//                String message = "I don't know what you mean";
//                throw new RuntimeException("I don't know what you mean");
//            }
//        }
        switch (dayString) {
            case "Saturday", "Sunday" -> {
                String message = "Weekend is here!";
                System.out.println(message);
            }
            case "Monday" -> {
                String message = "Weekend is not here!";
                System.out.println(message);
            }
            default -> {
                String message = "I don't know what you mean";
                throw new RuntimeException(message);
            }
        }

//        switch (dayString) {
//            case "Saturday", "Sunday":
//                String message = "Weekend is here!";
//                System.out.println(message);
//                break;
//            case "Monday":
//                String message = "Weekend is not here!";
//                System.out.println(message);
//            default:
//                String message = "I don't know what you mean";
//                throw new RuntimeException("I don't know what you mean");
//        }
//
//        System.out.println(weekend);
    }
}
