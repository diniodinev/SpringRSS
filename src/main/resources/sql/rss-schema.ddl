create table SITE (
    SITE_NAME varchar(256) NOT NULL,
    RSS_LINK varchar(256) NOT NULL,
    RSS_TAG varchar(64) NOT NULL,
    TITLE_TAG varchar(64) NULL,
    TEXT_CONTENT_TAG varchar(64) NOT NULL,
    primary key (SITE_NAME)

);

create table ARTICLE (
    ARTICLE_ID bigint IDENTITY(1,1) NOT NULL,
    FULL_LINK varchar(10) NOT NULL,
    ARTICLE_TEXT varchar(10),
    TITLE varchar(10),
    DATE DATETIME NULL,
    SITE_NAME varchar(256) NOT NULL,
    primary key (ARTICLE_ID),
    FOREIGN KEY (SITE_NAME) REFERENCES SITE(SITE_NAME)
    );









