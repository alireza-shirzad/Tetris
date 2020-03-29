
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreBoard {
    private static ScoreBoard scoreBoard;
    private int[] Scores;
    private final int MaxNumOfScores = 5;

    private ScoreBoard(){
        Scores = new int[MaxNumOfScores];
        Arrays.fill(Scores,0);
    }
    public static ScoreBoard getInstance() throws IOException {
        if(scoreBoard==null) {
            scoreBoard = new ScoreBoard();
            scoreBoard.setScores(scoreBoard.LoadScores());
        }
        return scoreBoard;
    }

    public void UpdateScores(int Score) throws IOException {
        insert(Score,Scores);
        SaveScore();
    }

    private void SaveScore() throws IOException {
        FileOperation.Write(JSONOperation.ObjectToJSON(scoreBoard));
    }
    public int[] LoadScores() throws IOException {
        ScoreBoard scoreBoard = (ScoreBoard) JSONOperation.JSONToObject(FileOperation.Read());
        return scoreBoard.getScores();
   }

    public int[] getScores() {
        return Scores;
    }

    public void setScores(int[] scores) {
        Scores = scores;
    }

    public int getMaxNumOfScores() {
        return MaxNumOfScores;
    }
    private  void insert(int val,int[] arr){
        int[] tmp = new int[MaxNumOfScores+1];
        for (int i = 0; i <MaxNumOfScores ; i++) {
            tmp[i] = arr[i];
        }
        tmp[MaxNumOfScores] = val;
        Arrays.sort(tmp);
        for (int i = MaxNumOfScores; i >0 ; i--) {
            arr[i-1]=tmp[i];
        }
    }

    private static void reverse(int a[])
    {
        int n = 5;
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

    }


}
