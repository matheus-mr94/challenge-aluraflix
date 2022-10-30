ALTER TABLE Video ADD category_id BIGINT NOT NULL;
ALTER TABLE Video ADD CONSTRAINT fk_video_category_id FOREIGN KEY (category_id) REFERENCES Category(id);