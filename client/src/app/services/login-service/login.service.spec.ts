import { HttpClient, HttpHandler } from '@angular/common/http';
import { async, inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LoginService } from './login.service';

describe('LoginService', () => {
let httpMock: HttpTestingController;
  let service: LoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [
        LoginService,
      ],
    });
    service = TestBed.get(LoginService);
    httpMock = TestBed.get(HttpTestingController);
  });
    it(`should fetch posts as an Observable`, async(inject([HttpTestingController, LoginService],
      (httpClient: HttpTestingController, loginService: LoginService) => {
       const user={
        username:'neelam',
        password:12345
       }
       
        const postItem = {
          "personalInfo": {
              "personId": 2,
              "personName": "neelamaa",
              "telephoneNumber": "112656276",
              "dob": "2022-11-06T18:30:00.000+00:00",
              "email": "nee2gmail.com",
              "gender": "MALE",
              "user": {
                  "userId": 6,
                  "username": "neelam",
                  "password": "12345",
                  "role": {
                      "roleId": 1,
                      "roleName": "manager"
                  }
              },
              "branch": {
                  "branchId": 1,
                  "branchName": "wadi",
                  "ifsc": "2sd2"
              },
              "address": {
                  "addressId": 2,
                  "flatNo": 13,
                  "city": "pune",
                  "state": "maharashtra",
                  "pincode": 41106
              }
          },
          "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZWVsYW0iLCJleHAiOjE2NzQ0OTM1NjQsImlhdCI6MTY3NDQ1NzU2NH0.ZRVyqNSsWHg-s_zPr5PBE3jz7vDXGvhbvMcZ3gn4498"
      }
  
        loginService.login(user)
          .subscribe((posts: any) => {
            expect(posts.length).toBe(1);
          });
        let req = httpMock.expectOne('http://localhost:8080/api/user/login');
        expect(req.request.method).toBe("POST");
        req.flush(postItem);
        httpMock.verify();
      })));
  });