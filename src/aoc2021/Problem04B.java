package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem04B {

    private static int calculateScore(Game game) {
        for (String draw : game.draws) {
            for (Board board: game.boards) {
                board.markCell(draw);
                if (game.isWinningLast())  {
                    return board.getScore(draw);
                }
                if (board.isWinning()) {
                    board.isWinning = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Game input1 = Game.stringArrayToGame(fileToStringArray(Problem04A.class, "Problem04Input1.txt"));
        Game input2 = Game.stringArrayToGame(fileToStringArray(Problem04B.class, "Problem04Input2.txt"));

        System.out.println(calculateScore(input1));
        System.out.println(calculateScore(input2));

//        Output
//        198
//        2498354
    }


    private static class Game {
        String[] draws;
        Board[] boards;

        public Game(String[] draws, Board[] boards) {
            this.draws = draws;
            this.boards = boards;
        }

        static Game stringArrayToGame(String[] strs) {
            String[] draws = strs[0].split(",");
            Board[] boards = new Board[(strs.length-1) / 6];
            for (int i = 0; i < boards.length; i++) {
                boards[i] = Board.createBoard(strs, (i*6) + 2);
            }
            return new Game(draws, boards);
        }

        private boolean isWinningLast() {
            int count = 0;
            for (Board board: boards) {
                if (board.isWinning) count++;
            }
            if (count != boards.length - 1) return false;
            for (Board board: boards) {
                if (!board.isWinning)
                    return board.isWinning();
            }
            return false;
        }
    }

    private static class Board {
        Cell[][] cells;
        boolean isWinning;

        public Board(Cell[][] cells) {
            this.cells = cells;
        }

        @Override
        public String toString() {
            String s = "";
            for (Cell[] cella: cells) {
                s += "[";
                for (Cell cell: cella) {
                    s += (cell.value +", ");
                }
                s += "]";
            }
            return s;
        }

        private static Board createBoard(String[] lines, int i) {
            Cell[][] cells = new Cell[5][5];
            for (int j = 0; j < cells.length; j++) {
                String[] numbers = lines[i+j].trim().split(" +");
                for (int k = 0; k < cells[j].length; k++) {
                    cells[j][k] = new Cell();
                    cells[j][k].value = Integer.parseInt(numbers[k]);
                }
            }
            return new Board(cells);
        }

        public void markCell(String draw) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j].value == Integer.parseInt(draw)) {
                        cells[i][j].marked = true;
                    }
                }
            }
        }

        public boolean isWinning() {
            boolean isWinning;
            for (int i = 0; i < cells.length; i++) {
                isWinning = true;
                for (int j = 0; j < cells[i].length; j++) {
                    if (!cells[i][j].marked) {
                        isWinning = false;
                        break;
                    }
                }
                if (isWinning) {
                    return true;
                }
            }
            for (int i = 0; i < cells.length; i++) {
                isWinning = true;
                for (int j = 0; j < cells[i].length; j++) {
                    if (!cells[j][i].marked) {
                        isWinning = false;
                        break;
                    }
                }
                if (isWinning) {
                    return true;
                }
            }
            return false;
        }

        public int getScore(String draw) {
            int drawInt = Integer.parseInt(draw);
            int sum = 0;
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (!cells[i][j].marked) {
                        sum += cells[i][j].value;
                    }
                }
            }
            return sum * drawInt;
        }
    }

    private static class Cell {
        int value;
        boolean marked;
    }
}
