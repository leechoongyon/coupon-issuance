import http from 'k6/http';
import { check } from 'k6';

export let options = {
  vus: 5, // vus 옵션은 가상 사용자 (Virtual User, VU)
    duration: "10s" // duration 옵션은 테스트를 실행할 총 시간. 여기서는 30초
};

export default function () {
    var url = "http://localhost:8080/api/v1/coupon/issuance/request";
//    const payload = { data: `user${i}` };
    const payload = { data: `user${Math.round(Math.random() * 100000)}` };
    const params = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    const res = http.post(url, JSON.stringify(payload), params);
    check(res, { 'status is 200': (r) => r.status === 200 });
}