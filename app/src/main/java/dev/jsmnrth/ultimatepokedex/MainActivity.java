package dev.jsmnrth.ultimatepokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import dev.jsmnrth.ultimatepokedex.api.API;
import dev.jsmnrth.ultimatepokedex.model.dto.request.LoginDto;
import dev.jsmnrth.ultimatepokedex.model.dto.response.ErrorDto;
import dev.jsmnrth.ultimatepokedex.model.dto.response.RefreshTokenDto;
import dev.jsmnrth.ultimatepokedex.model.dto.response.UserDto;
import dev.jsmnrth.ultimatepokedex.prefs.AppPreferences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppPreferences.initialize(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Username and Password cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        try {
            API.userApi()
                    .login(new LoginDto(username, password))
                    .enqueue(new Callback<RefreshTokenDto>() {
                        @Override
                        public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull Response<RefreshTokenDto> response) {
                            if (response.isSuccessful()) {
                                RefreshTokenDto refreshTokenDto = response.body();
                                if (refreshTokenDto != null) {
                                    Log.d("DEBUG", "Access Token: " + refreshTokenDto.getAccessToken());
                                    AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());

                                    // Navigate to HomeActivity
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish(); // Finish MainActivity to prevent returning to it
                                }
                            } else {
                                handleErrorResponse(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<RefreshTokenDto> call, Throwable t) {
                            Log.e("DEBUG", "Login failed", t);
                            Toast.makeText(MainActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Log.e("DEBUG", "Exception during login", e);
            Toast.makeText(MainActivity.this, "Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handleErrorResponse(Response<?> response) {
        ResponseBody errorBody = response.errorBody();
        try {
            if (errorBody != null) {
                String json = errorBody.string();
                ErrorDto errorDto = API.gson.fromJson(json, ErrorDto.class);
                Toast.makeText(MainActivity.this, errorDto.getDetail(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Unknown error occurred", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error parsing error response", Toast.LENGTH_SHORT).show();
        } finally {
            if (errorBody != null) {
                errorBody.close();
            }
        }
    }

    private void me() {
        API.userApi().me().enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(@NonNull Call<UserDto> call, @NonNull Response<UserDto> response) {
                if (response.isSuccessful()) {
                    UserDto userDto = response.body();
                    if (userDto != null) {
                        Log.d("DEBUG", "Username: " + userDto.getUsername());
                        Toast.makeText(MainActivity.this, "Hello, " + userDto.getUsername(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    handleErrorResponse(response);
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Log.e("DEBUG", "Failed to fetch user", t);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
