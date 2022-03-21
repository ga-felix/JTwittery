INSERT INTO User(name) VALUES('gabrielfelix');

INSERT INTO User(name) VALUES('pablo_ortellado');

INSERT INTO Tweet(id, text, created_at, author_name, likes, retweets, quotes, replies, type) VALUES(1, 'Eu acho que...', '2007-12-03T10:15:30+01:00', 'gabrielfelix', 10, 1, 0, 2, 'STANDARD');

INSERT INTO Tweet(id, text, created_at, author_name, likes, retweets, quotes, replies, type) VALUES(2, 'RT @gabrielfelix:Eu acho que...', '2007-12-04T10:15:30+01:00', 'pablo_ortellado', 3, 0, 0, 0, 'RETWEET');

INSERT INTO Referenced_Tweet(reference_type, id) VALUES('RETWEETED', 1);
INSERT INTO Tweet_Referenced(tweet_id, referenced_reference_type, referenced_id) VALUES(2, 'RETWEETED', 1);