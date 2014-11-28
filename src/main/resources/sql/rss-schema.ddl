create table ARTICLE (
    ARTICLE_ID bigint generated by default as identity(START WITH 1) ,
    FULL_LINK varchar(1024) NOT NULL,
    ARTICLE_TEXT varchar(8192),
    TITLE varchar(512),
    DATE DATETIME NULL,
    SITE_NAME varchar(256) NOT NULL,
    primary key (ARTICLE_ID)
);

create table SITE (
    SITE_NAME varchar(256) NOT NULL,
    RSS_LINK varchar(256) NOT NULL,
    RSS_TAG varchar(64) NOT NULL,
    TITLE_TAG varchar(64) NULL,
    TEXT_CONTENT_TAG varchar(64) NOT NULL,
    ARTICLE_ID bigint NULL,
    primary key (SITE_NAME),
    FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLE(ARTICLE_ID)
);




