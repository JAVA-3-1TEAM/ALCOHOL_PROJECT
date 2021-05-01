package kr.co.miniproject.mypage;

import java.sql.Date;

public class OrderlistMyPage {
//mypage: 
/*MYPAGE에 필요한것: 1. 주문목록
 * 현재 문제점
 * 
 * MYPAGE의 ORDERSLIST내용
주문일, 주문품목
ORGDATE, ORDER_NUM
SELECT O.ORGNUM AS "주문일",
O.ORDER_NUM AS "주문번호",
A.AL_ID AS "품목",
**여기 토탈프라이스 메서드로 빼놓고 컬럼없으면 엄청 복잡해지는데 이거 어떻게해야할 지 모르겠음**
WHERE ALCOHOL A, BASKET O, ORDERS O
WHERE B.AL_ID = A.AL=ID
AND B.BASKET_NUM = O.BASKET_NUM;

 * 2. REQUEST 목록: 글, 작성자, 작성일시간yy-mm-dd 24H
 * 3. comment
 * 
 * 
 * */ 
}