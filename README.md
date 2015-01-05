SpringRSS
=========

RSS Project using Spring

Deploy without tests:

mvn package tomcat7:run -Dmaven.test.skip=true

1. Get all Articles GET
http://localhost:9090/rss/article/

2. Get All Sites
http://localhost:8080/RSSRead/rss/site/

3. Get Article by ID
http://localhost:8080/RSSRead/rss/article/2

4. Get specific site (technews.bg)
http://localhost:8080/RSSRead/rss/site/technews.bg/

5. Search in Article titles by keyword (до key word)

http://localhost:8080/RSSRead/rss/article/search/до

6.Update site - PUT request
Headers for POSTMAN 

Content-Type  application/json
URL:
http://localhost:8080/RSSRead/rss/site/lentata.com/

message:

 for example
 {
 		"rssLink": "http://lentata.com/other/rss_dirbg.xml2",
        "rssTag": "lin2k",
        "titleTag": "title2",
        "textContentTag": "div.desc2",
        "categoryTag": "category2"
  }
  
7.  Post new site - POST request
Headers for POSTMAN 

Content-Type  application/json

URL:
http://localhost:8080/RSSRead/rss/site/

message:

{
    "siteName": "lentata.com",
    "rssLink": "http://lentata.com/other/rss_dirbg.xml",
    "rssTag": "link",
    "titleTag": "title",
    "textContentTag": "div.desc",
    "categoryTag": "category"
}

8. Delete site - DELETE request

URL:
http://localhost:8080/RSSRead/rss/site/delete/lentata.com/


  
 


