CREATE TABLE IF NOT EXISTS public.currency_tab(
    id bigserial NOT NULL primary key,
    checked_at timestamp without time zone NOT NULL,
    usd_value double precision NOT NULL,
    eur_value double precision NOT NULL,
    gbp_value double precision NOT NULL,
    jpy_value double precision NOT NULL
);

INSERT INTO public.currency_tab(
	id, checked_at, usd_value, eur_value, gbp_value, jpy_value)
	VALUES (1, now(), 1.0, 1.15, 0.76, 120.0087);

INSERT INTO public.currency_tab(
	id, checked_at, usd_value, eur_value, gbp_value, jpy_value)
	VALUES (2, now(), 2.0, 2.15, 2.76, 220.0087);

INSERT INTO public.currency_tab(
	id, checked_at, usd_value, eur_value, gbp_value, jpy_value)
	VALUES (3, now(), 3.0, 3.15, 3.76, 320.0087);

INSERT INTO public.currency_tab(
	id, checked_at, usd_value, eur_value, gbp_value, jpy_value)
	VALUES (4, '2035-06-22 19:10:25-07', 4.0, 4.15, 4.76, 420.0087);