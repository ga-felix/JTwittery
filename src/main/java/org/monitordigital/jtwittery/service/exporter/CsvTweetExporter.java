package org.monitordigital.jtwittery.service.exporter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.monitordigital.jtwittery.model.ReferencedTweet;
import org.monitordigital.jtwittery.model.Tweet;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service("csv")
public class CsvTweetExporter implements TweetExporter {

    private List<String> createHeader(Class tweetClass) {
        return new ArrayList<>(Arrays.asList(tweetClass.getDeclaredFields())
                .stream()
                .map(field -> field.toString())
                .collect(Collectors.toList()));
    }

    private List<String> tweetToStringList(Tweet tweet) {
        return new ArrayList<>(Arrays.asList(tweet.getId().toString(),
                tweet.getText(),
                tweet.getCreatedAt().toString(),
                tweet.getAuthor().getName(),
                tweet.getLikes().toString(),
                tweet.getRetweets().toString(),
                tweet.getQuotes().toString(),
                tweet.getReplies().toString(),
                tweet.getType().getType()));
    }

    private List<List<String>> createBody(List<Tweet> tweets) {
        List<List<String>> csvBody = new ArrayList<>();
        for(Tweet tweet : tweets) {
            if(Objects.isNull(tweet.getReferenced())) {
                List<String> stringTweet = tweetToStringList(tweet);
                stringTweet.add("");
                stringTweet.add("");
                csvBody.add(stringTweet);
                continue;
            }
            for(ReferencedTweet referenced : tweet.getReferenced()) {
                List<String> stringTweet = tweetToStringList(tweet);
                stringTweet.add(referenced.getTweet().getId().toString());
                stringTweet.add(referenced.getReferenceType().getType());
                csvBody.add(stringTweet);
            }
        }
        return csvBody;
    }

    public Map<String, Object> export(List<Tweet> tweets) {
        List<String> header = createHeader(tweets.get(0).getClass());
        header.add(("referenceType"));
        List<List<String>> body = createBody(tweets);
        ByteArrayInputStream byteArrayOutputStream;
        try(
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        CSVFormat.DEFAULT.withHeader(header.stream().toArray(String[]::new))
                );
        ) {
            for (List<String> record : body)
                csvPrinter.printRecord(record);
            csvPrinter.flush();
            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
        var responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/csv");
        responseHeaders.set("Content-Disposition", "attachment; filename=dataset.csv");
        return Map.of("header", responseHeaders, "dataset", fileInputStream);
    }

}
