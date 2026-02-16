package artiem.core.cli;

import artiem.core.engine.GameEngine;
import artiem.core.llm.InitialPromptRenderer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Artiem Core booting...");

        System.out.println(InitialPromptRenderer.render());
        System.out.println("\n[안내] 위 프롬프트를 ChatGPT에 먼저 붙여넣은 후, 게임을 시작하세요.\n");

        GameEngine engine = new GameEngine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("게임 종료");
                break;
            }

            engine.handleInput(input);
        }
    }
}
