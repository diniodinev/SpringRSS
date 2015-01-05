# SpringRSS

*RSS Project using Spring*

##### Deploy without tests:

 ```maven
mvn package tomcat7:run -Dmaven.test.skip=true
 ```

##### POSTAMN requests
1. Get all Articles GET <br />
[http://localhost:9090/rss/article/](http://localhost:9090/rss/article/)<br />
2. Get All Sites<br />
http://localhost:8080/RSSRead/rss/site/
3. Get Article by ID<br />
http://localhost:8080/RSSRead/rss/article/2
4. Get specific site (technews.bg)<br />
http://localhost:8080/RSSRead/rss/site/technews.bg/
5. Search in Article titles by keyword (до key word)<br />
http://localhost:8080/RSSRead/rss/article/search/до
6. Update site - PUT request<br />
Headers for POSTMAN <br />
Content-Type  application/json<br />
URL:<br />
http://localhost:8080/RSSRead/rss/site/lentata.com/
message:
for example<br />
 ```JSON
 {
 		"rssLink": "http://lentata.com/other/rss_dirbg.xml2",
        "rssTag": "lin2k",
        "titleTag": "title2",
        "textContentTag": "div.desc2",
        "categoryTag": "category2"
  }
  ```<br />
 7. Post new site - POST request<br />
Headers for POSTMAN 
Content-Type  application/json
URL:
http://localhost:8080/RSSRead/rss/site/
message:<br />
```JSON
{
    "siteName": "lentata.com",
    "rssLink": "http://lentata.com/other/rss_dirbg.xml",
    "rssTag": "link",
    "titleTag": "title",
    "textContentTag": "div.desc",
    "categoryTag": "category"
}
```
8. Delete site - DELETE request<br />
URL:<br />
http://localhost:8080/RSSRead/rss/site/delete/lentata.com/



