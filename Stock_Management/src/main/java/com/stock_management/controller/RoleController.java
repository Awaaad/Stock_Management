package com.stock_management.controller;

import com.stock_management.dto.RoleDto;
import com.stock_management.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("all")
    public  ResponseEntity<List<RoleDto>>getAllRoles(){
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @PostMapping("save-role")
    public ResponseEntity<String> saveUser(@RequestBody RoleDto roleDto){
        roleService.saveRole(roleDto);
        return new ResponseEntity<>("Role saved successfully!", HttpStatus.OK);
    }
}
