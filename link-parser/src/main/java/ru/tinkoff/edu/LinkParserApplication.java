package ru.tinkoff.edu;

import ru.tinkoff.edu.parser.GitHubLinkParser;

public class LinkParserApplication
{
     public static GitHubLinkParser gitHubLinkParser = new GitHubLinkParser(null);
    public static void main( String[] args ) {
        Record record1 = gitHubLinkParser.parseLink("https://github.com/KAMILSHANGAREEV");
        System.out.println(record1);
    }
}
