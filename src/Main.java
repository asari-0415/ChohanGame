import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=== 丁半博打ゲーム ===");
        System.out.println("サイコロ2個の合計が「丁（偶数）」か「半（奇数）」かを当てよう！");
        System.out.println("コマンド: 丁 / 半 / exit（終了）");
        System.out.println("-----------------------------------");

        boolean playAgain = true;

        while (playAgain) {
            int money = 1000; // 初期所持金
            System.out.println("初期所持金: " + money + "円");

            while (true) {
                if (money <= 0) {
                    System.out.println("所持金がなくなりました… ゲームオーバー。");
                    break;
                }

                System.out.print("賭け金を入力（数字のみ / exitで終了）> ");
                String betStr = sc.nextLine().trim();

                // 🔹どの時点でもexit入力で完全終了
                if (betStr.equalsIgnoreCase("exit")) {
                    System.out.println("ゲーム終了。おつかれさまでした！");
                    sc.close();
                    return; // ← すべてのループを抜けて終了
                }

                int bet;
                try {
                    bet = Integer.parseInt(betStr);
                } catch (NumberFormatException e) {
                    System.out.println("数字で入力してください。");
                    continue;
                }

                if (bet <= 0) {
                    System.out.println("0円以下は賭けられません。");
                    continue;
                }
                if (bet > money) {
                    System.out.println("そんなに賭けられません！（現在: " + money + "円）");
                    continue;
                }

                System.out.print("丁（偶数）か半（奇数）を入力（exitで終了）> ");
                String choice = sc.nextLine().trim();

                if (choice.equalsIgnoreCase("exit")) {
                    System.out.println("ゲーム終了。おつかれさまでした！");
                    sc.close();
                    return; // ← ここでも完全終了
                }

                if (!choice.equals("丁") && !choice.equals("半")) {
                    System.out.println("「丁」か「半」で入力してください。");
                    continue;
                }

                // サイコロを振る
                int dice1 = rand.nextInt(6) + 1;
                int dice2 = rand.nextInt(6) + 1;
                int total = dice1 + dice2;
                boolean isEven = (total % 2 == 0);

                System.out.println("サイコロの出目: [" + dice1 + "] と [" + dice2 + "] → 合計 " + total);
                String result = isEven ? "丁" : "半";
                System.out.println("結果は・・・「" + result + "」！！");

                if (choice.equals(result)) {
                    int win = bet;
                    money += win;
                    System.out.println("当たり！ " + win + "円の儲け！");
                } else {
                    money -= bet;
                    System.out.println("ハズレ… " + bet + "円失いました。");
                }

                System.out.println("現在の所持金: " + money + "円");
                System.out.println("-----------------------------------");
            }

            // ★再スタート確認
            while (true) {
                System.out.print("もう一度遊びますか？ (y/n / exit) > ");
                String again = sc.nextLine().trim();

                // 全角英字もOK
                again = again.replace("ｙ", "y").replace("ｎ", "n").replace("ｅ", "e").replace("ｘ", "x").replace("ｉ", "i").replace("ｔ", "t");
                again = again.toLowerCase();

                if (again.equals("exit")) {
                    System.out.println("ゲーム終了。おつかれさまでした！");
                    sc.close();
                    return;
                } else if (again.equals("y")) {
                    playAgain = true;
                    System.out.println("\n--- 新しいゲームを開始します！ ---\n");
                    break;
                } else if (again.equals("n")) {
                    playAgain = false;
                    System.out.println("また遊んでね！");
                    break;
                } else {
                    System.out.println("y か n か exit で入力してください。");
                }
            }
        }

        sc.close();
    }
}
