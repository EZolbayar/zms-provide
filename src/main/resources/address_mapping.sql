create table ADDRESS_MAPPING
(
  city_code          VARCHAR2(255 CHAR),
  city_code_xyp      VARCHAR2(255 CHAR),
  city_name          VARCHAR2(255 CHAR),
  district_code      VARCHAR2(255 CHAR) not null,
  district_code_xyp  VARCHAR2(255 CHAR),
  district_name      VARCHAR2(255 CHAR),
)
tablespace TSBG
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table ADDRESS_MAPPING
  add primary key (DISTRICT_CODE)
  using index 
  tablespace TSBG
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '12000', '01', N'Багануур дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '12300', '04', N'Багахангай дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '16000', '07', N'Баянгол дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '13000', '10', N'Баянзүрх дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '12600', '13', N'Налайх дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '18000', '16', N'Сонгинохайрхан дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '14000', '19', N'Сүхбаатар дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '17000', '22', N'Хан-Уул дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('11000', '11', N'Улаанбаатар', '15000', '25', N'Чингэлтэй дүүрэг');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21160', '10', N'Баян-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21120', '04', N'Баяндун сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21050', '07', N'Баянтүмэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21090', '13', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21080', '16', N'Гурванзагал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21100', '19', N'Дашбалбар сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21020', '22', N'Матад сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21110', '25', N'Сэргэлэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21010', '28', N'Халх гол сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21150', '31', N'Хөлөнбуйр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21060', '01', N'Хэрлэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21130', '34', N'Цагаан-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21040', '37', N'Чойбалсан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('21000', '21', N'Дорнод аймаг', '21030', '40', N'Чулуунхороот сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22050', '04', N'Асгат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22070', '01', N'Баруун-Урт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22110', '07', N'Баяндэлгэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22040', '10', N'Дарьганга сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22140', '13', N'Мөнххаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22060', '16', N'Наран сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22090', '19', N'Онгон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22030', '22', N'Сүхбааатар сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22160', '25', N'Түвшинширээ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22150', '28', N'Түмэнцогт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22130', '31', N'Уулбаян сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22100', '34', N'Халзан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('22000', '22', N'Сүхбаатар аймаг', '22010', '37', N'Эрдэнэцагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23040', '04', N'Батноров сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23160', '07', N'Батширээт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23070', '10', N'Баян-Адарга сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23170', '13', N'Баянмөнх сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23010', '16', N'Баян-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23060', '19', N'Баянхутаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23100', '22', N'Биндэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23220', '52', N'Бор-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23080', '25', N'Галшир сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23030', '28', N'Дадал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23180', '31', N'Дархан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23200', '34', N'Дэлгэрхаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23190', '37', N'Жаргалтхаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23130', '40', N'Мөрөн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23020', '43', N'Норовлин сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23140', '46', N'Өмнөдэлгэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23110', '01', N'Хэрлэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('23000', '23', N'Хэнтий аймаг', '23210', '49', N'Цэнхэрмандал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41130', '04', N'Алтанбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41180', '07', N'Аргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41060', '10', N'Архуст сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41120', '13', N'Батсүмбэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41140', '25', N'Баян-Өнжүүл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41070', '16', N'Баян сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41030', '19', N'Баяндэлгэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41020', '22', N'Баянжаргалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41210', '28', N'Баянхангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41080', '31', N'Баянцагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41170', '34', N'Баянцогт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41160', '37', N'Баянчандмань сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41150', '40', N'Борнуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41260', '43', N'Бүрэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41280', '46', N'Дэлгэрхаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41200', '49', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41270', '52', N'Заамар сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41100', '01', N'Зуунмод сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41240', '55', N'Лүн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41010', '58', N'Мөнгөнморьт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41250', '61', N'Өндөрширээт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41190', '64', N'Сүмбэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41090', '67', N'Сэргэлэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41220', '70', N'Угтаалцайдам сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41230', '73', N'Цээл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41040', '76', N'Эрдэнэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('41000', '41', N'Төв аймаг', '41290', '79', N'Эрдэнэсант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('42000', '42', N'Говьсүмбэр аймаг', '42040', '04', N'Баянтал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('42000', '42', N'Говьсүмбэр аймаг', '42010', '01', N'Сүмбэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('42000', '42', N'Говьсүмбэр аймаг', '42030', '07', N'Шивээговь сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43040', '04', N'Алтанбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43170', '07', N'Баруунбүрэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43060', '10', N'Баянгол сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43010', '13', N'Ерөө сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43050', '16', N'Жавхлант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43090', '19', N'Зүүнбүрэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43030', '22', N'Мандал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43140', '25', N'Орхон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43160', '28', N'Орхонтуул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43130', '31', N'Сайхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43150', '34', N'Сант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43080', '01', N'Сүхбаатар сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43100', '37', N'Түшиг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43120', '43', N'Хушаат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43020', '40', N'Хүдэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43110', '46', N'Цагааннуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('43000', '43', N'Сэлэнгэ аймаг', '43070', '49', N'Шаамар сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44120', '04', N'Айраг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44070', '07', N'Алтанширээ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44130', '10', N'Даланжаргалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44040', '13', N'Дэлгэрэх сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44010', '16', N'Замын-Үүд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44090', '19', N'Иххэт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44140', '22', N'Мандах сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44030', '25', N'Өргөн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44100', '01', N'Сайншанд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44110', '28', N'Сайхандулаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44050', '31', N'Улаанбадрах сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44080', '34', N'Хатанбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44060', '37', N'Хөвсгөл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('44000', '44', N'Дорноговь аймаг', '44020', '40', N'Эрдэнэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('45000', '45', N'Дархан-Уул аймаг', '45040', '01', N'Дархан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('45000', '45', N'Дархан-Уул аймаг', '45030', '04', N'Орхон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('45000', '45', N'Дархан-Уул аймаг', '45020', '07', N'Хонгор сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('45000', '45', N'Дархан-Уул аймаг', '45010', '10', N'Шарын гол сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46120', '07', N'Баян-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46030', '04', N'Баяндалай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46110', '10', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46150', '13', N'Гурвантэс сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46080', '01', N'Даланзадгад сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46090', '16', N'Мандал-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46020', '19', N'Манлай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46140', '22', N'Ноён сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46050', '25', N'Номгон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46130', '28', N'Сэврэй сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46010', '31', N'Ханбогд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46070', '34', N'Ханхонгор сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46100', '37', N'Хүрмэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46060', '40', N'Цогт-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('46000', '46', N'Өмнөговь аймаг', '46040', '43', N'Цогтцэций сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48140', '04', N'Адаацаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48020', '07', N'Баянжаргалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48070', '10', N'Говь-Угтаал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48060', '13', N'Гурвансайхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48170', '16', N'Дэлгэрхангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48110', '19', N'Дэлгэрцогт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48080', '22', N'Дэрэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48120', '25', N'Луус сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48040', '28', N'Өлзийт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48010', '31', N'Өндөршил сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48090', '01', N'Сайнцагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48180', '34', N'Сайхан-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48130', '37', N'Хулд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48030', '40', N'Цагаандэлгэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('48000', '48', N'Дундговь аймаг', '48150', '43', N'Эрдэнэдалай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('61000', '61', N'Орхон аймаг', '61020', '01', N'Баян-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('61000', '61', N'Орхон аймаг', '61010', '04', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62160', '01', N'Арвайхээр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62270', '04', N'Баруунбаян-Улаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62210', '07', N'Бат-Өлзий сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62010', '13', N'Баян-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62060', '10', N'Баянгол сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62190', '16', N'Богд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62020', '19', N'Бүрд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62180', '22', N'Гучин-Ус сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62030', '25', N'Есөнзүйл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62120', '28', N'Зүүнбаян-Улаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62260', '31', N'Нарийнтээл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62050', '34', N'Өлзийт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62040', '37', N'Сант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62140', '40', N'Тарагт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62080', '43', N'Төгрөг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62220', '46', N'Уянга сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62240', '49', N'Хайрхандулаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62090', '52', N'Хархорин сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('62000', '62', N'Өвөрхангай аймаг', '62110', '55', N'Хужирт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63180', '04', N'Баян-Агт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63010', '07', N'Баяннуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63090', '10', N'Бугат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63080', '01', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63050', '13', N'Бүрэгхангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63120', '16', N'Гурванбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63060', '19', N'Дашинчилэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63150', '22', N'Могод сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63040', '25', N'Орхон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63070', '28', N'Рашаант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63160', '31', N'Сайхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63020', '34', N'Сэлэнгэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63100', '37', N'Тэшиг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63030', '40', N'Хангал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63110', '43', N'Хишиг-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('63000', '63', N'Булган аймаг', '63140', '46', N'Хутаг-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64150', '04', N'Баацагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64080', '16', N'Баян-Овоо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64250', '19', N'Баян-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64260', '07', N'Баянбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64130', '10', N'Баянговь сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64010', '13', N'Баянлиг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64090', '01', N'Баянхонгор сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64210', '22', N'Баянцагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64050', '25', N'Богд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64170', '28', N'Бөмбөгөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64220', '31', N'Бууцагаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64110', '34', N'Галуут сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64230', '37', N'Гурванбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64180', '40', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64070', '43', N'Жинст сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64200', '46', N'Заг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64030', '49', N'Өлзийт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64240', '52', N'Хүрээмарал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64190', '55', N'Шинэжинст сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('64000', '64', N'Баянхонгор аймаг', '64020', '58', N'Эрдэнэцогт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65100', '65', N'Архангай аймаг', '65000', '04', N'Батцэнгэл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65120', '07', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65200', '10', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65170', '13', N'Ихтамир сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65020', '16', N'Өгийнуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65030', '19', N'Өлзийт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65210', '22', N'Өндөр-Улаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65230', '25', N'Тариат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65070', '28', N'Төвшрүүлэх сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65060', '31', N'Хайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65250', '34', N'Хангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65010', '37', N'Хашаат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65040', '40', N'Хотонт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65270', '43', N'Цахир сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65080', '46', N'Цэнхэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65190', '49', N'Цэцэрлэг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65220', '52', N'Чулуут сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65130', '01', N'Эрдэнэбулган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('65000', '65', N'Архангай аймаг', '65150', '55', N'Эрдэнэмандал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67140', '04', N'Алаг-Эрдэнэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67200', '07', N'Арбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67290', '10', N'Баянзүрх сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67220', '13', N'Бүрэнтогтох сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67190', '16', N'Галт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67250', '19', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67050', '22', N'Их-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67120', '01', N'Мөрөн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67060', '25', N'Рашаант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67170', '28', N'Рэнчинлхүмбэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67010', '31', N'Тариалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67110', '34', N'Тосонцэнгэл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67160', '37', N'Төмөрбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67090', '40', N'Түнэл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67270', '43', N'Улаан-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67070', '46', N'Ханх сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67340', '70', N'Хатгал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67300', '52', N'Цагаан-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67040', '55', N'Цагаан-Үүр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67260', '49', N'Цагааннуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67320', '58', N'Цэцэрлэг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67080', '61', N'Чандмань-Өндөр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67240', '64', N'Шинэ-Идэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('67000', '67', N'Хөвсгөл аймаг', '67030', '67', N'Эрдэнэбулган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81190', '04', N'Алдархаан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81170', '07', N'Асгат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81160', '10', N'Баянтэс сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81180', '13', N'Баянхайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81280', '16', N'Дөрвөлжин сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81270', '19', N'Завханмандал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81060', '22', N'Идэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81010', '25', N'Их-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81110', '28', N'Нөмрөг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81070', '31', N'Отгон сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81260', '34', N'Сантмаргац сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81230', '37', N'Сонгино сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81030', '40', N'Тосонцэнгэл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81210', '43', N'Түдэвтэй сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81050', '46', N'Тэлмэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81250', '49', N'Тэс сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81090', '01', N'Улиастай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81290', '52', N'Ургамал сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81120', '55', N'Цагаанхайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81150', '58', N'Цагаанчулуут сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81220', '61', N'Цэцэн-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81140', '64', N'Шилүүстэй сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81240', '67', N'Эрдэнэхайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('81000', '81', N'Завхан аймаг', '81130', '70', N'Яруу сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82130', '04', N'Алтай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82150', '07', N'Баян-Уул сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82040', '10', N'Бигэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82200', '13', N'Бугат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82190', '16', N'Дарив сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82010', '19', N'Дэлгэр сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82080', '01', N'Есөнбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82110', '22', N'Жаргалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82070', '25', N'Тайшир сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82210', '28', N'Тонхил сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82170', '31', N'Төгрөг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82100', '34', N'Халиун сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82180', '37', N'Хөхморьт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82050', '40', N'Цогт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82120', '43', N'Цээл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82020', '46', N'Чандмань сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82140', '49', N'Шарга сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('82000', '82', N'Говь-Алтай аймаг', '82030', '52', N'Эрдэнэ сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83150', '04', N'Алтай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83040', '07', N'Алтанцөгц сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83030', '10', N'Баяннуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83050', '13', N'Бугат сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83010', '16', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83140', '19', N'Буянт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83060', '22', N'Дэлүүн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83100', '25', N'Ногооннуур сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83120', '01', N'Өлгий сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83160', '28', N'Сагсай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83080', '31', N'Толбо сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83170', '34', N'Улаанхус сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('83000', '83', N'Баян-Өлгий аймаг', '83190', '37', N'Цэнгэл сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84080', '04', N'Алтай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84210', '07', N'Булган сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84090', '10', N'Буянт сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84010', '13', N'Дарви  сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84020', '16', N'Дөргөн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84200', '19', N'Дуут сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84140', '01', N'Жаргалант сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84060', '22', N'Зэрэг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84110', '25', N'Манхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84190', '28', N'Мөнххайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84070', '31', N'Мөст сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84100', '34', N'Мянгад сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84130', '37', N'Үенч сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84170', '40', N'Ховд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84050', '43', N'Цэцэг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84030', '46', N'Чандмань сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('84000', '84', N'Ховд аймаг', '84180', '49', N'Эрдэнэбүрэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85020', '04', N'Баруунтуруун сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85220', '07', N'Бөхмөрөн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85120', '10', N'Давст сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85100', '13', N'Завхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85040', '16', N'Зүүнговь сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85010', '19', N'Зүүнхангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85090', '22', N'Малчин сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85110', '25', N'Наранбулаг сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85150', '28', N'Өлгий сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85210', '31', N'Өмнөговь сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85030', '34', N'Өндөрхангай сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85190', '37', N'Сагил сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85130', '40', N'Тариалан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85200', '43', N'Түргэн сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85060', '46', N'Тэс сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85160', '01', N'Улаангом сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85230', '49', N'Ховд сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85080', '52', N'Хяргас сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('85000', '85', N'Увс аймаг', '85050', '55', N'Цагаанхайрхан сум');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('BJING', '', N'BEJING', 'BJING', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('FRKRT', '', N'Frankfurt', 'FRKRT', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('HONKG', '', N'HONGKONG', 'HONKG', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('MNTRL', '', N'MONTREAL', 'MNTRL', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('MSCOW', '', N'MOSCOW', 'MSCOW', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('SDNY', '', N'SYDENY', 'SDNY', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('SEOUL', '', N'SEOUL', 'SEOUL', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('SNGPR', '', N'SINGAPORE', 'SNGPR', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('TOKYO', '', N'TOKYO', 'TOKYO', '', '');
insert into address_mapping (CITY_CODE, CITY_CODE_XYP, CITY_NAME, DISTRICT_CODE, DISTRICT_CODE_XYP, DISTRICT_NAME) 
values('OTHER', '', N'Other', '99000', '', '');
commit;

