import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GoogleSearch {

    //Array list to hold all the urls that were fetched from a google search
    public static ArrayList<String> urls = new ArrayList<>();

    //Array list to store all the links that are functioning
    public static ArrayList<String> filteredUrls = new ArrayList<>();

    //Constructor method
    public GoogleSearch() throws IOException {

        //String variables used to store queries for google searches
        String query = "https://www.google.com/search?q=tips+on+reducing+" + MindSpace_Test.suggestionFileName;
        String query2 = "https://www.google.com/search?q=tips+on+reducing+general+mental+stress";
        String query3 = "https://www.google.com/search?q=tips+on+increasing+focus";

        String source;

        //If the file name has a name of 'Other'
        if (MindSpace_Test.suggestionFileName.equals("Other")) {

            //Make the search query set to the second one
            source = getURLSource(query2);

            //If the file name has a name of 'Focus'
        } else if (MindSpace_Test.suggestionFileName.equals("Focus")){

            //Make the search query set to the third one
            source = getURLSource(query3);

        } else

            //Just use the first query for all other files
            source = getURLSource(query);

        //Locate and store the strings(urls) found in the page of html code
        String[] split1 = source.split("source=web&amp;rct=j&amp;url=");

        //cycle through the 15 websites
        for (int i = 2; i < 15; i++) {

                //store each one in the inital search array
                urls.add(split1[i].split("&amp;ved=")[0]);
        }

        //Scan through all the websites in the inital array
        for(int x = 0; x < urls.size(); x++){

            //If the website urls contain this
            if(urls.get(x).contains("webcache")){

                //remove them
                urls.remove(x);

            }
        }

        workingUrls();

    }

    //A method used used to filter the non-working urls
    private void workingUrls (){

        for(String element : urls) {

            if(!filteredUrls.contains(element)) {

                filteredUrls.add(element);

            }
        }
    }

    //A method used to create a client that will be used when surfing the wev
    public static String getURLSource(String url) throws IOException {

        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());

    }

    //To-String method
    private static String toString(InputStream inputStream) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();

        }
    }
}