CREATE TABLE SITE (
    SITE_NAME VARCHAR(256) NOT NULL,
    RSS_LINK VARCHAR(256) NOT NULL,
    RSS_TAG VARCHAR(64) NOT NULL,
    TITLE_TAG VARCHAR(64) NULL,
    TEXT_CONTENT_TAG VARCHAR(64) NOT NULL,
    CATEGORY_TAG VARCHAR(128) NULL,
    PRIMARY KEY (SITE_NAME)
);

CREATE TABLE ARTICLE (
    ARTICLE_ID BIGINT IDENTITY(1,1) NOT NULL,
    FULL_LINK VARCHAR(256) NOT NULL,
    ARTICLE_TEXT VARCHAR(MAX),
    TITLE VARCHAR(512) NULL,
    PUBLICATION_DATE DATETIME NULL,
    SITE_NAME VARCHAR(256) NOT NULL,
    PRIMARY KEY (ARTICLE_ID),
    FOREIGN KEY (SITE_NAME) REFERENCES SITE(SITE_NAME)
    );

CREATE TABLE CATEGORY (
  CATEGORY_ID BIGINT IDENTITY(1,1) NOT NULL,
  CATEGORY_NAME VARCHAR(64) NULL,
  PRIMARY KEY (CATEGORY_ID)
);

CREATE TABLE ARTICLE_CATEGORY (
  ART_ID BIGINT NOT NULL,
  CAT_ID BIGINT NOT NULL,
  PRIMARY KEY (ART_ID, CAT_ID),
  FOREIGN KEY (ART_ID) REFERENCES ARTICLE(ARTICLE_ID),
  FOREIGN KEY (CAT_ID) REFERENCES CATEGORY(CATEGORY_ID)
);


