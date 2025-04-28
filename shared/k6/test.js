import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  scenarios: {
    normal: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "normal",
      gracefulStop: '0s',
    },
    notFound: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "notFound",
      gracefulStop: '0s',
      startTime: '10s'
    },
    error: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "error",
      gracefulStop: '0s',
      startTime: '20s'
    }
  }
};

const host = "http://host.docker.internal:8080";

export function normal() {
  http.get(host + "/prices?applicationDate=2025-06-14T10:00:00.000&productId=35455&brandId=1");
  sleep(0.5);
}


export function notFound() {
  http.get(host + "/prices?applicationDate=2025-06-14T10:00:00.000&productId=35455&brandId=2");
  sleep(0.5);
}

export function error() {
  http.get(host + "/prices?applicationDate=2025-00:00:00.000&productId=35455&brandId=1");
  sleep(0.5);
}