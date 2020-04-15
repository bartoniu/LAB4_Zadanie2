import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class Zadanie2 {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int n = Integer.parseInt(args[0]);
        double median = Double.parseDouble(args[1]);
        double sigma = Double.parseDouble(args[2]);

        if (sigma <= 0)
        {
            throw new AssertionError();
        }

        //

        File file = new File("binary.bin");

        if (file.createNewFile())
        {
            System.out.println("File .bin is created!");
        } else {
            System.out.println("File .bin already exists.");
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        DataOutputStream data = new DataOutputStream(outputStream);

        for(int i = 0; i < n; i++)
        {
            data.writeDouble(random.nextGaussian()*sigma+median);
        }

        File txtFile = new File("numbers.txt");

        if (txtFile.createNewFile())
        {
            System.out.println("File .txt is created!");
        } else {
            System.out.println("File .txt already exists.");
        }

        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        DataOutputStream txtData = new DataOutputStream(new FileOutputStream(txtFile));

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator(',');
        String pattern = "#.#######";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, unusualSymbols);
        decimalFormat.setGroupingSize(999);

        Double inputLine;

        while(inputStream.available() > 0)
        {
            inputLine = inputStream.readDouble();
            System.out.println(inputLine);

            txtData.writeBytes(decimalFormat.format(inputLine) + "\n");
        }

        outputStream.close();
        data.close();
        inputStream.close();
        txtData.close();
    }
}
