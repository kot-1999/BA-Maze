package game.gameLogic.controller;

import java.io.*;


public enum Key {
//            System.out.println("Click <p> to play | <r> to see players raiting | <c> to see comments");
    UP,LEFT,DOWN,RIGHT,EXIT,ENTER, R_KEY, C_KEY,
    //error key
    NOTAKEY;
    private static InputStreamReader reader = new InputStreamReader(System.in);

    public static Key convertKey(String str){
        switch (str){
            case "w":
            case "KeyW":
            case "ArrowUp":
                return UP;
            case "a":
            case "KeyA":
            case "ArrowLeft":
                return LEFT;
            case "s":
            case "KeyS":
            case "ArrowDown":
                return DOWN;
            case "d":
            case "KeyD":
            case "ArrowRight":
                return RIGHT;
            case "q":
            case "KeyQ":
            case "Escape":
                return EXIT;
            case "f":
            case "KeyF":
            case "Enter":
                return ENTER;
            case "r":
            case "KeyR":
                return R_KEY;
            case "c":
            case "KeyC":
                return C_KEY;
            default:
                return NOTAKEY;
        }
    }

    public static Key getKey() {
        char c=0;
        try {
            c = (char) reader.read();
            if(c=='\n')
                return getKey();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        switch (c){
            case 'w':
                return UP;
            case 'a':
                return LEFT;
            case 's':
                return DOWN;
            case 'd':
                return RIGHT;
            case 'q':
                return EXIT;
            case 'f':
                return ENTER;
            case 'r':
                return R_KEY;
            case 'c':
                return C_KEY;
                default:
                return NOTAKEY;
        }
    }


}
