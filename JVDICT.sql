-- CREATE TABLE IF NOT EXISTS JVDICTEX ( 
--    Id 		INT UNSIGNED not null,
--    JvdictId INT UNSIGNED not null,
--    Sentence	nvarchar (50) not null,
--    Meaning 	nvarchar (1000) not null,
--    PRIMARY KEY (Id),
--    FOREIGN KEY (JvdictId) REFERENCES JVDICT (Ijvdictd)
-- );
-- INSERT INTO JVDICTEX (Id, JvdictId, Sentence, Meaning) VALUES (1,1,"その計画を新聞に暴く","phơi bày kế hoạch trên báo chị́");
-- INSERT INTO JVDICTEX (Id, JvdictId, Sentence, Meaning) VALUES (2,1,"この手紙は彼らの居場所を暴くものだ","bức thư này làm lộ rõ chỗ ở của họ");
-- ctrl+/ to comment  
CREATE TABLE IF NOT EXISTS JVDICT (
   Id 		INT UNSIGNED not null,
   HikaWord	nvarchar (100),
   Kanji 	nvarchar (100),
   Spell 	nvarchar (100) not null,
   Meaning 	nvarchar (1000) not null,
   Level 	INT UNSIGNED,
   PRIMARY KEY (Id)
);
INSERT INTO JVDICT (Id, HikaWord, Kanji, Spell , Meaning, Level) VALUES (1,"あばく","暴く","abaku"," vạch trần; phơi bày; bộc lộ; làm lộ",0);

CREATE TABLE IF NOT EXISTS TEST (
   Id 		INT UNSIGNED not null,
   Kanji 	nvarchar (100),
   Hira 	nvarchar (100),
   Level 	INT UNSIGNED not null,
   PRIMARY KEY (Id)
);
INSERT INTO TEST(Id,Kanji,Hira,Level) VALUES(	1	,"	嗚呼	","	ああ	",1);