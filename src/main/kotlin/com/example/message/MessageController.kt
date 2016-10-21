package com.example.message

import com.example.account.Account
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/message")
open class MessageController constructor(val messageService: MessageService) {

    @ModelAttribute("messages")
    open fun messages(@AuthenticationPrincipal account: Account?) : List<Message> = messageService.findAll()

    @ModelAttribute
    open fun account(@AuthenticationPrincipal account: Account?) : Account? = account

    @GetMapping
    open fun findAll(model: Model, @AuthenticationPrincipal account: Account?): String {
        return "message"
    }

    @PostMapping
    open fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult, @AuthenticationPrincipal account: Account?): String {
        if(bindingResult.hasErrors()){
            return "message"
        }
        messageService.save(messageForm, account)
        return "redirect:/message"
    }
}