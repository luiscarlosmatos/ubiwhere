
create table review_score
(
  establishment_id bigint           not null
    constraint review_score_pkey
      primary key,
  average_score    double precision not null,
  reviews_count    bigint           not null
);

create table reviews
(
  review_id               bigserial not null
    constraint reviews_pkey
      primary key,
  created_at              timestamp not null,
  updated_at              timestamp not null,
  review_details          text      not null,
  review_establishment_id bigint    not null,
  review_score            bigint    not null
    constraint reviews_review_score_check
      check ((review_score <= 5) AND (review_score >= 1))
);


INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (1, '2019-01-21 20:26:49.921000', '2019-01-21 20:26:49.921000', 'some text', 134047, 5);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (2, '2019-01-21 20:26:55.424000', '2019-01-21 20:26:55.424000', 'some text', 134047, 4);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (3, '2019-01-21 20:27:05.511000', '2019-01-21 20:27:05.511000', 'some text', 134047, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (4, '2019-01-21 20:27:06.659000', '2019-01-21 20:27:06.659000', 'some text', 134047, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (5, '2019-01-21 20:27:07.273000', '2019-01-21 20:27:07.273000', 'some text', 134047, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (6, '2019-01-21 20:29:01.188000', '2019-01-21 20:29:01.188000', 'some text', 9228, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (7, '2019-01-21 20:29:01.529000', '2019-01-21 20:29:01.529000', 'some text', 9228, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (8, '2019-01-21 20:29:01.879000', '2019-01-21 20:29:01.879000', 'some text', 9228, 3);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (9, '2019-01-21 20:29:07.402000', '2019-01-21 20:29:07.402000', 'some text', 9228, 2);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (10, '2019-01-21 20:29:07.642000', '2019-01-21 20:29:07.642000', 'some text', 9228, 2);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (11, '2019-01-21 20:29:13.001000', '2019-01-21 20:29:13.001000', 'some text', 9228, 1);
INSERT INTO public.reviews (review_id, created_at, updated_at, review_details, review_establishment_id, review_score) VALUES (12, '2019-01-21 20:29:18.441000', '2019-01-21 20:29:18.441000', 'some text', 9228, 5);
INSERT INTO public.review_score (establishment_id, average_score, reviews_count) VALUES (134047, 3.6, 5);
INSERT INTO public.review_score (establishment_id, average_score, reviews_count) VALUES (9228, 2.7142857142857144, 7);