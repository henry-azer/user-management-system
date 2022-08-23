package org.henry.controller;

import org.henry.dto.UserDto;
import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.dto.request.PaginationRequest;
import org.henry.dto.response.PaginationResponse;
import org.henry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        Optional<UserDto> user = Optional.ofNullable(userService.findById(id));

        return user.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();

        return users.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/find-all-paginated")
    public ResponseEntity<PaginationResponse<UserDto>> findAllUsersPaginated(@RequestBody PaginationRequest paginationRequest) {
        return new ResponseEntity<>(userService.findAllUsersPaginatedRequest(paginationRequest), HttpStatus.OK);
    }

    @PostMapping("/find-all-paginated-filtered")
    public ResponseEntity<PaginationResponse<UserDto>> findAllUsersPaginatedAndFiltered(@RequestBody FilterPaginationRequest<UserPaginationFiltrationDto> userPaginationFiltrationDto) {
        return new ResponseEntity<>(userService.findAllUsersPaginatedFiltered(userPaginationFiltrationDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        Optional<UserDto> user = Optional.ofNullable(userService.findById(userDto.getId()));


        return user.isPresent() ?
                new ResponseEntity<>(userService.update(userDto, userDto.getId()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}