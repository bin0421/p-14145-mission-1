import java.util.ArrayList;
import java.util.Scanner;

class Quote {
    int id;
    String content;
    String author;

    Quote(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);
        ArrayList<Quote> quotes = new ArrayList<>();
        int lastId = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            }

            if (cmd.equals("등록")) {
                lastId++;
                int id = lastId;

                System.out.print("명언) ");
                String content = sc.nextLine().trim();

                System.out.print("작가) ");
                String author = sc.nextLine().trim();

                Quote quote = new Quote(id, content, author);
                quotes.add(quote);

                System.out.println(id + "번 명언이 등록되었습니다.");
            }

            else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-----------------");

                if (quotes.isEmpty()) {
                    System.out.println("등록된 명언이 없습니다.");
                } else {

                    for (int i = quotes.size() - 1; i >= 0; i--) {
                        Quote q = quotes.get(i);
                        System.out.println(q.id + " / " + q.author + " / " + q.content);
                    }
                }
            }

            else if (cmd.startsWith("삭제?id=")) {
                try {
                    String[] parts = cmd.split("=");
                    int targetId = Integer.parseInt(parts[1]);

                    Quote targetQuote = null;
                    for (Quote q : quotes) {
                        if (q.id == targetId) {
                            targetQuote = q;
                            break;
                        }
                    }

                    if (targetQuote == null) {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    } else {
                        quotes.remove(targetQuote);
                        System.out.println(targetId + "번 명언이 삭제되었습니다.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("올바른 번호를 입력해주세요.");
                }
            }

            else if (cmd.startsWith("수정?id=")) {
                try {
                    String[] parts = cmd.split("=");
                    int targetId = Integer.parseInt(parts[1]);

                    Quote targetQuote = null;
                    for (Quote q : quotes) {
                        if (q.id == targetId) {
                            targetQuote = q;
                            break;
                        }
                    }

                    if (targetQuote == null) {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    } else {
                        System.out.println("명언(기존) : " + targetQuote.content);
                        System.out.print("명언) ");
                        String content = sc.nextLine().trim();

                        System.out.println("작가(기존) : " + targetQuote.author);
                        System.out.print("작가) ");
                        String author = sc.nextLine().trim();

                        targetQuote.content = content;
                        targetQuote.author = author;

                        System.out.println(targetId + "번 명언이 수정되었습니다.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("올바른 번호를 입력해주세요.");
                }
            }
        }

        sc.close();
    }
}