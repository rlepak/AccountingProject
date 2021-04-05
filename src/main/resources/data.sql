insert into role (id, description, enabled)
values (1, 'Admin', true),
       (2, 'Manager', true),
       (3, 'Employee', true);


insert into state (code, name)
values ('AL', 'Alabama');
insert into state (code, name)
values ('AK', 'Alaska');
insert into state (code, name)
values ('AS', 'American Samoa');
insert into state (code, name)
values ('AZ', 'Arizona');
insert into state (code, name)
values ('AR', 'Arkansas');
insert into state (code, name)
values ('CA', 'California');
insert into state (code, name)
values ('CO', 'Colorado');
insert into state (code, name)
values ('CT', 'Connecticut');
insert into state (code, name)
values ('DE', 'Delaware');
insert into state (code, name)
values ('DC', 'District of Columbia');
insert into state (code, name)
values ('FM', 'Federated States of Micronesia');
insert into state (code, name)
values ('FL', 'Florida');
insert into state (code, name)
values ('GA', 'Georgia');
insert into state (code, name)
values ('GU', 'Guam');
insert into state (code, name)
values ('HI', 'Hawaii');
insert into state (code, name)
values ('ID', 'Idaho');
insert into state (code, name)
values ('IL', 'Illinois');
insert into state (code, name)
values ('IN', 'Indiana');
insert into state (code, name)
values ('IA', 'Iowa');
insert into state (code, name)
values ('KS', 'Kansas');
insert into state (code, name)
values ('KY', 'Kentucky');
insert into state (code, name)
values ('LA', 'Louisiana');
insert into state (code, name)
values ('ME', 'Maine');
insert into state (code, name)
values ('MH', 'Marshall Islands');
insert into state (code, name)
values ('MD', 'Maryland');
insert into state (code, name)
values ('MA', 'Massachusetts');
insert into state (code, name)
values ('MI', 'Michigan');
insert into state (code, name)
values ('MN', 'Minnesota');
insert into state (code, name)
values ('MS', 'Mississippi');
insert into state (code, name)
values ('MO', 'Missouri');
insert into state (code, name)
values ('MT', 'Montana');
insert into state (code, name)
values ('NE', 'Nebraska');
insert into state (code, name)
values ('NV', 'Nevada');
insert into state (code, name)
values ('NH', 'New Hampshire');
insert into state (code, name)
values ('NJ', 'New Jersey');
insert into state (code, name)
values ('NM', 'New Mexico');
insert into state (code, name)
values ('NY', 'New York');
insert into state (code, name)
values ('NC', 'North Carolina');
insert into state (code, name)
values ('ND', 'North Dakota');
insert into state (code, name)
values ('MP', 'Northern Mariana Islands');
insert into state (code, name)
values ('OH', 'Ohio');
insert into state (code, name)
values ('OK', 'Oklahoma');
insert into state (code, name)
values ('OR', 'Oregon');
insert into state (code, name)
values ('PW', 'Palau');
insert into state (code, name)
values ('PA', 'Pennsylvania');
insert into state (code, name)
values ('PR', 'Puerto Rico');
insert into state (code, name)
values ('RI', 'Rhode Island');
insert into state (code, name)
values ('SC', 'South Carolina');
insert into state (code, name)
values ('SD', 'South Dakota');
insert into state (code, name)
values ('TN', 'Tennessee');
insert into state (code, name)
values ('TX', 'Texas');
insert into state (code, name)
values ('UT', 'Utah');
insert into state (code, name)
values ('VT', 'Vermont');
insert into state (code, name)
values ('VI', 'Virgin Islands');
insert into state (code, name)
values ('VA', 'Virginia');
insert into state (code, name)
values ('WA', 'Washington');
insert into state (code, name)
values ('WV', 'West Virginia');
insert into state (code, name)
values ('WI', 'Wisconsin');
insert into state (code, name)
values ('WY', 'Wyoming');

insert into company(created_by, created_time, is_deleted, updated_by, updated_time, title, address, state, zip,
                    representative, email, established_date, status, phone)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'LG', '110 Main', 'IL', '60022', 'Nick Smith',
        'Lg@gmail.com', '2021-01-05 00:00:00', 'ACTIVE', '770-032-2343'),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Samsung', '120 North', 'IL', '60022', 'Bob Smith',
        'Samsung@gmail.com', '2021-01-05 00:00:00', 'CLOSED', '770-032-2343'),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Nokia', '110 Roosvelt', 'IL', '60022',
        'James Smith', 'Nokia@gmail.com', '2021-01-05 00:00:00', 'ACTIVE', '770-032-2343');



insert into users(created_by, created_time, is_deleted, updated_by, updated_time, email, first_name, last_name,
                  password, phone, status, company_id, role_id)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'user1@google.com', 'Nick', 'Smith', 'abc1',
        '7739823232', 'ACTIVE', 1, 1),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'user2@google.com', 'Bob', 'Smith', 'abc1',
        '7739823232', 'ACTIVE', 3, 1),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'user3@google.com', 'James', 'Great', 'abc1',
        '7739823232', 'ACTIVE', 2, 1);

insert into vendor_client (created_by, created_time, is_deleted, updated_by, updated_time, address, company_name, email,
                           phone, status, type, zip_code, company_id, state)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', '100 Main', 'New way', 'newway@gmail.net',
        '7739823232', 'ACTIVE', 'VENDOR', '60132', 1, 'WI'),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', '234 Chicago', 'Easy', 'easy@gmail.net',
        '5634563456', 'ACTIVE', 'CLIENT', '60544', 2, 'FL'),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', '500 North', 'Good Luck', 'good@gmail.net',
        '35643654', 'ACTIVE', 'VENDOR', '60132', 3, 'IL');

insert into category (created_by, created_time, is_deleted, updated_by, updated_time, description, status, company_id)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Electronic', 'ACTIVE' , 3),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Food', 'ACTIVE' , 2),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Automotive', 'ACTIVE' , 3),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Industrial Goods', 'ACTIVE' , 1),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Furniture', 'ACTIVE' , 2),
       (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Home Improvement', 'ACTIVE' , 1);

insert into product (created_by, created_time, is_deleted, updated_by, updated_time, description, low_limit_alert, name,
                           price, quantity,  status, tax, unit, category_id, company_id)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', 'Smart TV', FALSE , 'TV',
        123, 4, 'ACTIVE', 9.5, 'PIECE', 1, 1);


insert into invoice (created_by, created_time, is_deleted, updated_by, updated_time, invoice_date, invoice_number, invoice_type,
                      company_id, vendor_client_id, status)
values (2, '2021-01-05 00:00:00', false, 2, '2021-01-05 00:00:00', '2021-01-05', 23 , 'PURCHASE',
         1, 1, 'ACTIVE');
