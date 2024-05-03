INSERT INTO public.product_topic (id, uuid, code, display_name) VALUES (1, e'dcf162b0-e9f5-42f0-bd48-dea65fb3f037', 'SMARTPHONE__GADGETS', 'Smartphony i Gadżety');

INSERT INTO public.product_topic_category (id, uuid, product_topic_id, code, display_name)
VALUES (DEFAULT, 'f946dfb8-b6aa-4f48-be0d-3e8554d0e317', 1, 'PHONES', 'Smartfony i telefony');

INSERT INTO public.products (id, uuid, display_name, base_price) VALUES (1, '6b28e67d-38d9-47cf-98a1-b5077cce2341', 'Iphone 15 pro max', 590000);
INSERT INTO public.products_products_categories (product_id, product_category_id) VALUES (1, 1);

INSERT INTO public.product_specification_categories (id, uuid, name, product_id)
VALUES (DEFAULT, '376a98a4-b139-41fa-9e6d-ec4b3f4df1c7', 'Dane Podstawowe', 1);

