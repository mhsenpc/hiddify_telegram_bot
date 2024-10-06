# hiddify-telegram-bot

# معرفی اجمالی
این نرم افزار یک ربات تلگرام است که به شما امکان استفاده از پنل Hiddify می دهد

با استفاده از این اکانت می توانید به سادگی در پنل هیدیفای خود اکانت تعریف کنید



### راه اندازی روی سرور
* ساخت ربات تلگرامی از طریق https://t.me/BotFather
* نصب Docker, Docker compose
* دانلود Docker compose
* `curl -L https://raw.githubusercontent.com/mhsenpc/hiddify_telegram_bot/main/docker-compose.yml -o docker-compose.yml`
* `mkdir storage`
* `nano storage/config.json`
```
{
    "BOT_HOST_URL": "https://b113-37-201-192-98.ngrok-free.app",
    "BASE_URL": "https://erer.qweqwe.site/434343",
    "API_KEY": "37e21c57-qwewqe-4a5c-we-qwe",
    "BOT_TOKEN": "aaweqwew",
    "PROFILE_LINK": "https://erer.qweqwe.site/awewe/%s/#%s"
}
```
* docker-compose up -d

## Generate self signed certificate
برای ارتباط با سرورهای تلگرام ربات شما نیاز به یک ارتباط امن از نوع HTTPS دارد. برای تولید کلید همچنین نیاز به یک دامنه دارید. دامنه خود را با bot.ferfere.de عوض کنید و دستورات زیر را اچرا کنید
```
openssl req -x509 -newkey rsa:4096 -keyout private.key -out cert.crt -days 365 -nodes -subj "/CN=bot.ferfere.de"
openssl pkcs12 -export -out keystore.p12 -inkey private.key -in cert.crt -name myalias
```


    

## تصاویری از ربات

![image](https://github.com/user-attachments/assets/2a3fdf0d-e729-4bcf-878f-55a222b9bfb1)

![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/1a101033-87fc-47de-a837-13e08f41a7b6)
![image](https://github.com/user-attachments/assets/5d30395d-0fe2-45a6-8851-131042f4b850)
