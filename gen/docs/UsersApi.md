# UsersApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiUsersGet**](UsersApi.md#apiUsersGet) | **GET** /api/users | Get All User
[**apiUsersPost**](UsersApi.md#apiUsersPost) | **POST** /api/users | Create a new user


<a name="apiUsersGet"></a>
# **apiUsersGet**
> apiUsersGet(page)

Get All User

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    UsersApi apiInstance = new UsersApi(defaultClient);
    Integer page = 1; // Integer | Номер
    try {
      apiInstance.apiUsersGet(page);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#apiUsersGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**| Номер | [optional] [default to 1]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Lis of user |  -  |

<a name="apiUsersPost"></a>
# **apiUsersPost**
> apiUsersPost(userDTO)

Create a new user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    UsersApi apiInstance = new UsersApi(defaultClient);
    UserDTO userDTO = new UserDTO(); // UserDTO | User object to create
    try {
      apiInstance.apiUsersPost(userDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsersApi#apiUsersPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userDTO** | [**UserDTO**](UserDTO.md)| User object to create |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | User created successfully |  -  |

