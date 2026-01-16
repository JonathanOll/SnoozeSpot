# snoozespot_api.api.DefaultApi

## Load the API package
```dart
import 'package:snoozespot_api/api.dart';
```

All URIs are relative to *https://snoozespot_api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authLoginPost**](DefaultApi.md#authloginpost) | **POST** /auth/login | 
[**authSignupPost**](DefaultApi.md#authsignuppost) | **POST** /auth/signup | 
[**postsCommentCommentIdDelete**](DefaultApi.md#postscommentcommentiddelete) | **DELETE** /posts/comment/{commentId} | 
[**postsGet**](DefaultApi.md#postsget) | **GET** /posts | 
[**postsIdCommentPost**](DefaultApi.md#postsidcommentpost) | **POST** /posts/{id}/comment | 
[**postsIdDelete**](DefaultApi.md#postsiddelete) | **DELETE** /posts/{id} | 
[**postsIdGet**](DefaultApi.md#postsidget) | **GET** /posts/{id} | 
[**postsIdLikePost**](DefaultApi.md#postsidlikepost) | **POST** /posts/{id}/like | 
[**postsPost**](DefaultApi.md#postspost) | **POST** /posts | 
[**spotsGet**](DefaultApi.md#spotsget) | **GET** /spots | 
[**spotsIdCommentPost**](DefaultApi.md#spotsidcommentpost) | **POST** /spots/{id}/comment | 
[**spotsIdGet**](DefaultApi.md#spotsidget) | **GET** /spots/{id} | 
[**spotsPost**](DefaultApi.md#spotspost) | **POST** /spots | 
[**spotsZoneGet**](DefaultApi.md#spotszoneget) | **GET** /spots/zone | 
[**usersGet**](DefaultApi.md#usersget) | **GET** /users | 
[**usersMeGet**](DefaultApi.md#usersmeget) | **GET** /users/me | 
[**usersProfilePicturePost**](DefaultApi.md#usersprofilepicturepost) | **POST** /users/profile-picture | 
[**usersUuidGet**](DefaultApi.md#usersuuidget) | **GET** /users/{uuid} | 


# **authLoginPost**
> AuthResponseDTO authLoginPost(userAuthRequest)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final UserAuthRequest userAuthRequest = ; // UserAuthRequest | 

try {
    final response = api.authLoginPost(userAuthRequest);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->authLoginPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userAuthRequest** | [**UserAuthRequest**](UserAuthRequest.md)|  | 

### Return type

[**AuthResponseDTO**](AuthResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **authSignupPost**
> AuthResponseDTO authSignupPost(userAuthRequest)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final UserAuthRequest userAuthRequest = ; // UserAuthRequest | 

try {
    final response = api.authSignupPost(userAuthRequest);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->authSignupPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userAuthRequest** | [**UserAuthRequest**](UserAuthRequest.md)|  | 

### Return type

[**AuthResponseDTO**](AuthResponseDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsCommentCommentIdDelete**
> JsonObject postsCommentCommentIdDelete(commentId)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int commentId = 56; // int | 

try {
    final response = api.postsCommentCommentIdDelete(commentId);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsCommentCommentIdDelete: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **commentId** | **int**|  | 

### Return type

[**JsonObject**](JsonObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsGet**
> BuiltList<PostDTO> postsGet(page)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int page = 56; // int | 

try {
    final response = api.postsGet(page);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **int**|  | [optional] 

### Return type

[**BuiltList&lt;PostDTO&gt;**](PostDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsIdCommentPost**
> PostCommentDTO postsIdCommentPost(id, createPostCommentRequest)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 
final CreatePostCommentRequest createPostCommentRequest = ; // CreatePostCommentRequest | 

try {
    final response = api.postsIdCommentPost(id, createPostCommentRequest);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsIdCommentPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 
 **createPostCommentRequest** | [**CreatePostCommentRequest**](CreatePostCommentRequest.md)|  | 

### Return type

[**PostCommentDTO**](PostCommentDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsIdDelete**
> JsonObject postsIdDelete(id)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 

try {
    final response = api.postsIdDelete(id);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsIdDelete: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**JsonObject**](JsonObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsIdGet**
> PostDTO postsIdGet(id)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 

try {
    final response = api.postsIdGet(id);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsIdGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**PostDTO**](PostDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsIdLikePost**
> bool postsIdLikePost(id)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 

try {
    final response = api.postsIdLikePost(id);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsIdLikePost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

**bool**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postsPost**
> PostDTO postsPost(content, file)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final String content = content_example; // String | Contenu textuel du post
final MultipartFile file = BINARY_DATA_HERE; // MultipartFile | Fichier optionnel associé au post

try {
    final response = api.postsPost(content, file);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->postsPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **content** | **String**| Contenu textuel du post | 
 **file** | **MultipartFile**| Fichier optionnel associé au post | [optional] 

### Return type

[**PostDTO**](PostDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: text/plain, */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **spotsGet**
> BuiltList<SpotDTO> spotsGet()





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();

try {
    final response = api.spotsGet();
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->spotsGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BuiltList&lt;SpotDTO&gt;**](SpotDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **spotsIdCommentPost**
> SpotCommentDTO spotsIdCommentPost(id, createSpotCommentRequest)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 
final CreateSpotCommentRequest createSpotCommentRequest = ; // CreateSpotCommentRequest | 

try {
    final response = api.spotsIdCommentPost(id, createSpotCommentRequest);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->spotsIdCommentPost: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 
 **createSpotCommentRequest** | [**CreateSpotCommentRequest**](CreateSpotCommentRequest.md)|  | 

### Return type

[**SpotCommentDTO**](SpotCommentDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **spotsIdGet**
> SpotDTO spotsIdGet(id)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final int id = 56; // int | 

try {
    final response = api.spotsIdGet(id);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->spotsIdGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **int**|  | 

### Return type

[**SpotDTO**](SpotDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **spotsPost**
> SpotDTO spotsPost()





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();

try {
    final response = api.spotsPost();
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->spotsPost: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SpotDTO**](SpotDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **spotsZoneGet**
> BuiltList<SpotDTO> spotsZoneGet(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final num topLeftLatitude = 8.14; // num | 
final num topLeftLongitude = 8.14; // num | 
final num bottomRightLatitude = 8.14; // num | 
final num bottomRightLongitude = 8.14; // num | 

try {
    final response = api.spotsZoneGet(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->spotsZoneGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **topLeftLatitude** | **num**|  | [optional] 
 **topLeftLongitude** | **num**|  | [optional] 
 **bottomRightLatitude** | **num**|  | [optional] 
 **bottomRightLongitude** | **num**|  | [optional] 

### Return type

[**BuiltList&lt;SpotDTO&gt;**](SpotDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersGet**
> BuiltList<UserDTO> usersGet()





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();

try {
    final response = api.usersGet();
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->usersGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**BuiltList&lt;UserDTO&gt;**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersMeGet**
> UserDTO usersMeGet()





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();

try {
    final response = api.usersMeGet();
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->usersMeGet: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersProfilePicturePost**
> String usersProfilePicturePost()





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();

try {
    final response = api.usersProfilePicturePost();
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->usersProfilePicturePost: $e\n');
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **usersUuidGet**
> UserDTO usersUuidGet(uuid)





### Example
```dart
import 'package:snoozespot_api/api.dart';

final api = SnoozespotApi().getDefaultApi();
final String uuid = uuid_example; // String | 

try {
    final response = api.usersUuidGet(uuid);
    print(response);
} catch on DioException (e) {
    print('Exception when calling DefaultApi->usersUuidGet: $e\n');
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uuid** | **String**|  | 

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

