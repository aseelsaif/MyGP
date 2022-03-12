package DeailDetail.DealDetailsFX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

public class CSVWriter {
	private static final Logger LOGGER = Logger.getLogger(CSVWriter.class);
    private static final String COMMA = ",";
    private static final String DEFAULT_SEPARATOR = COMMA;
    private static final String DOUBLE_QUOTES = "\"";
    private static final String EMBEDDED_DOUBLE_QUOTES = "\"\"";
    private static final String NEW_LINE_UNIX = "\n";
    private static final String NEW_LINE_WINDOWS = "\r\n";


    public String convertToCsvFormat(final String[] line) {
		LOGGER.info("Convrting to CSV file...");

        return convertToCsvFormat(line, DEFAULT_SEPARATOR);
    }

    public String convertToCsvFormat(final String[] line, final String separator) {
        return convertToCsvFormat(line, separator, true);
    }

    public String convertToCsvFormat(
            final String[] line,
            final String separator,
            final boolean quote) {

        return Stream.of(line)                              // convert String[] to stream
                .map(l -> formatCsvField(l, quote))         // format CSV field
                .collect(Collectors.joining(separator));    // join with a separator

    }

    private String formatCsvField(final String field, final boolean quote) {

        String result = field;

        if (result.contains(COMMA)
                || result.contains(DOUBLE_QUOTES)
                || result.contains(NEW_LINE_UNIX)
                || result.contains(NEW_LINE_WINDOWS)) {
            result = result.replace(DOUBLE_QUOTES, EMBEDDED_DOUBLE_QUOTES);
            result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;

        } else {
            if (quote) {
                result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;
            }
        }

        return result;

    }
    void writeToCsvFile(List<String[]> list, File file)  {
    	int check = 1;
    	LOGGER.info("Writing to CSV file...");
    	boolean isFileEmty = true;
        List<String> collect = list.stream()
                .map(this::convertToCsvFormat)
                .collect(Collectors.toList());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() != null) {
            	isFileEmty = false;}
            br.close();
        } catch (IOException e) {
        	assert true;
        	LOGGER.error(e.getMessage(), e);
//            e.printStackTrace();
        }
        
//        try {(BufferedReader br = new BufferedReader(new FileReader(file) ):if(br.readLine()==null) {})};
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
        	if(isFileEmty) {
            for (String line : collect) {
                bw.write(line);
                bw.newLine();
            }
        	}
        	else {
        		
        		for (String line : collect) {
        			if (check ==1) {
        				check++;
        			}
        			else {
                    bw.write(line);
                    bw.newLine();
        			}
                }
        	}
        	bw.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
        	LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}

    }

}