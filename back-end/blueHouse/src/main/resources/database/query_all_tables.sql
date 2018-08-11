SELECT o.user_id AS user_id, o.id AS order_id, m.id AS id, m.name AS name, m.url AS url, m.ts AS ts, m.crew AS crew FROM t_orders AS o INNER JOIN t_order_items AS oi ON o.id = oi.order_id INNER JOIN t_measures AS m ON oi.item_id = m.id WHERE o.id = "ord1" AND o.user_id = "usr8";

SELECT o.user_id AS user_id, o.id AS order_id, c.id AS id, c.name AS name, c.url AS url, c.ts AS ts, c.type AS type FROM t_orders AS o INNER JOIN t_order_items AS oi ON o.id = oi.order_id INNER JOIN t_contracts AS c ON oi.item_id = c.id WHERE o.id = "ord1" AND o.user_id = "usr8";

SELECT o.user_id AS user_id, o.id AS order_id, d.id AS id, d.name AS name, d.url AS url, d.ts AS ts, d.designer AS designer FROM t_orders AS o INNER JOIN t_order_items AS oi ON o.id = oi.order_id INNER JOIN t_designs AS d ON oi.item_id = d.id WHERE o.id = "ord1" AND o.user_id = "usr8";

SELECT o.user_id AS user_id, o.id AS order_id, d.id AS id, d.name AS name, d.url AS url, d.ts AS ts FROM t_orders AS o INNER JOIN t_order_items AS oi ON o.id = oi.order_id INNER JOIN t_disclaims AS d ON oi.item_id = d.id WHERE o.id = "ord1" AND o.user_id = "usr8";
