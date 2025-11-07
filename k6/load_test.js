// load_test.js (Скрипт остается без изменений)
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  // Увеличиваем нагрузку до 300 VUs для поиска предела
  stages: [
    // 1. Быстрый подъем до 100 VU за 10 секунд
    { duration: '10s', target: 100 },
    // 2. Удержание на 100 VU в течение 10 секунд
    { duration: '20s', target: 100 },
  ],
  // Общая продолжительность теста: 65 секунд

  thresholds: {
    // Порог 95-го перцентиля времени ответа
    'http_req_duration': ['p(95) < 100'],
    // Порог ошибок
    'http_req_failed': ['rate < 0.01'],
  },
};

export default function () {
  const randomValue = Math.floor(Math.random() * 1000);
  const payload = JSON.stringify({
    value: randomValue,
  });
  const params = {
    headers: { 'Content-Type': 'application/json' },
  };

  http.post('http://host.docker.internal:8080/api/numbers', payload, params);
  sleep(0.10);
}