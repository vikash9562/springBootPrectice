package com.rays.ctl;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDto;
import com.rays.dto.UserDto;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserForm, UserDto, UserServiceInt> {

	@Autowired
	public AttachmentServiceInt attachmentService;
	
	@Autowired
	public UserServiceInt userService;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSucess()) {
			return res;
		}

		UserDto dto = (UserDto) form.getDto();
		if (dto.getId() != null && dto.getId() > 0) {
			userservice.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Updated Successfully..!!");
		} else {
			long pk = userservice.add(dto);
			res.addData(pk);
			res.addMessage("Data added Successfully..!!");
		}
		return res;
	}


	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDto attachmentDto = new AttachmentDto(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDto userDto = userservice.findById(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {

			attachmentDto.setId(userDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDto);

		if (userDto.getImageId() == null) {

			userDto.setImageId(imageId);

			userservice.update(userDto);

		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);

		return res;

	}
	
	@GetMapping("/profilePic/{userId}")
	public @ResponseBody void downloadPic(@PathVariable Long userId, HttpServletResponse response) {
		
		try {
				
			UserDto userDto = userService.findById(userId);
			
			AttachmentDto attachmentDto = null;
			
			if (userDto != null) {
				attachmentDto = attachmentService.findById(userDto.getImageId());
			}
			
			if (attachmentDto != null) {
				response.setContentType(attachmentDto.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDto.getDoc());
				out.close();
			}else {
				response.getWriter().write("ERROR: file not found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
