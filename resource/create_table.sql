drop table news_info;
create table news_info (
id int primary key AUTO_INCREMENT,
news_day date not null,
news_title varchar(200)
);

drop table daily_area_price;
create table daily_area_price(
id int primary key AUTO_INCREMENT, 
news_date_id int references news_info(id),
provence_id int references promary(proID),
price1  DECIMAL(10,5), 
price2  DECIMAL(10,5), 
price3  DECIMAL(10,5), 
price4  DECIMAL(10,5), 
price5  DECIMAL(10,5)
);
