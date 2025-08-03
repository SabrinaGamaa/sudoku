package model;

import java.util.Scanner;

public class SudokuGame {
    private SudokuBoard board;
    private GameStatus status = GameStatus.NAO_INICIADO;

    public SudokuGame(SudokuBoard board) {
        this.board = board;
        this.status = GameStatus.INCOMPLETO;
    }

    public void menu() {
        System.out.println("\uD83E\uDD29 Bem-vindo ao Sudoku!");

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Iniciar novo jogo");
            System.out.println("2. Colocar número");
            System.out.println("3. Remover número");
            System.out.println("4. Ver jogo");
            System.out.println("5. Verificar status");
            System.out.println("6. Limpar");
            System.out.println("7. Finalizar jogo");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    board.clearUserInputs();
                    board.printBoard();
                    break;

                case 2:
                    board.printBoard();
                    System.out.print("Linha: ");
                    int l = sc.nextInt();
                    System.out.print("Coluna: ");
                    int c = sc.nextInt();
                    System.out.print("Valor: ");
                    int v = sc.nextInt();

                    boolean sucesso = board.setValue(l, c, v);
                    if (sucesso) {
                        System.out.println("Número adicionado.");
                    } else {
                        System.out.println("Falha ao adicionar número.");
                    }
                    board.printBoard();
                    break;

                case 3:
                    System.out.print("Linha: ");
                    l = sc.nextInt();
                    System.out.print("Coluna: ");
                    c = sc.nextInt();
                    if (board.removeValue(l, c)) {
                        System.out.println("Número removido.");
                        board.printBoard();
                    } else {
                        System.out.println("Não pode remover valor fixo.");
                    }
                    break;

                case 4:
                    board.printBoard();
                    break;

                case 5:
                    System.out.println("Status: " + status);
                    if (board.hasErrors()) {
                        System.out.println("⚠️ O jogo contém erros!");
                    } else {
                        System.out.println("✅ Sem erros até o momento.");
                    }
                    break;

                case 6:
                    board.clearUserInputs();
                    System.out.println("Todos os números informados pelo usuário foram apagados.");
                    board.printBoard();
                    break;

                case 7:
                    if (board.isComplete() && !board.hasErrors()) {
                        status = GameStatus.COMPLETO;
                        System.out.println("🎉 Você finalizou corretamente o Sudoku!");
                        break;
                    } else {
                        System.out.println("Ainda há espaços vazios ou erros.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7 || status != GameStatus.COMPLETO);
    }
}

