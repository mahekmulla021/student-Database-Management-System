package studentdb;

import java.io.*;

public class ResumeReader {

    public static ResumeData readResume(File file)
            throws Exception {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(file));

        String line;

        String name = "";
        String email = "";
        String degree = "";
        String skills = "";

        while((line = br.readLine()) != null) {

            if(line.startsWith("Name:"))
                name = line.substring(5).trim();

            else if(line.startsWith("Email:"))
                email = line.substring(6).trim();

           

            else if(line.startsWith("Degree:"))
                degree = line.substring(7).trim();

            else if(line.startsWith("Skills:"))
                skills = line.substring(7).trim();
        }

        br.close();

        return new ResumeData(
                name,
                email,
                degree,
                skills);
    }
}