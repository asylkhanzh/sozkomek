-- Table: accounts

-- DROP TABLE accounts;

CREATE TABLE accounts
(
  id integer NOT NULL,
  name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  path character varying(255) NOT NULL,
  accounttype character varying(100) NOT NULL,
  CONSTRAINT accounts_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE accounts
OWNER TO postgres;

-- Table: comments

-- DROP TABLE comments;

CREATE TABLE comments
(
  id integer NOT NULL,
  goods_id integer NOT NULL,
  account_id integer NOT NULL,
  comment character varying(1000) NOT NULL,
  status integer NOT NULL,
  CONSTRAINT comment_pk PRIMARY KEY (id),
  CONSTRAINT account_k FOREIGN KEY (account_id)
  REFERENCES accounts (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT goods_k FOREIGN KEY (goods_id)
  REFERENCES goods (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE comments
OWNER TO postgres;

-- Table: goods

-- DROP TABLE goods;

CREATE TABLE goods
(
  id integer NOT NULL,
  name character varying(255) NOT NULL,
  price integer,
  description character varying(1000),
  CONSTRAINT goods_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE goods
OWNER TO postgres;


-- Table: orders

-- DROP TABLE orders;

CREATE TABLE orders
(
  id bigint NOT NULL,
  account_id integer NOT NULL,
  transactiontype_id integer NOT NULL,
  transactionprocess_id integer NOT NULL,
  approvalcode character varying(255),
  bankorder character varying,
  reference character varying(255),
  phone integer,
  addres character varying(500),
  CONSTRAINT orders_id PRIMARY KEY (id),
  CONSTRAINT order_acccount_id FOREIGN KEY (account_id)
  REFERENCES accounts (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT orders_transactionprocess_id FOREIGN KEY (transactionprocess_id)
  REFERENCES transactionprocess (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT orders_transationtype_id FOREIGN KEY (transactiontype_id)
  REFERENCES transactiontype (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE orders
OWNER TO postgres;


-- Table: ordersgoods

-- DROP TABLE ordersgoods;

CREATE TABLE ordersgoods
(
  id bigint NOT NULL,
  amount numeric(255,2) NOT NULL,
  orders_id bigint NOT NULL,
  goods_id integer NOT NULL,
  CONSTRAINT ordersgoods_id PRIMARY KEY (id),
  CONSTRAINT ordersgoods_goods_id FOREIGN KEY (goods_id)
  REFERENCES goods (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ordersgoods_orders_id FOREIGN KEY (orders_id)
  REFERENCES orders (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE ordersgoods
OWNER TO postgres;


-- Table: transactionprocess

-- DROP TABLE transactionprocess;

CREATE TABLE transactionprocess
(
  id integer NOT NULL,
  name character varying(100) NOT NULL,
  description character varying(255) NOT NULL,
  CONSTRAINT transactionprocess_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE transactionprocess
OWNER TO postgres;


-- Table: transactiontype

-- DROP TABLE transactiontype;

CREATE TABLE transactiontype
(
  id integer NOT NULL,
  name character varying(100) NOT NULL,
  description character varying(255) NOT NULL,
  CONSTRAINT transactiontype_pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE transactiontype
OWNER TO postgres;
