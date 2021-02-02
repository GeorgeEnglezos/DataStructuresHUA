import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    private static int[] freq= new int[128];

    public static void main(String[] args) throws IOException {
        for (int i=0;i<freq.length;i++)
        {
            freq[i]=0;
        }
        Text.ExtractText(freq);

    }

}
