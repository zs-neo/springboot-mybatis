# springboot整合mybatis+nginx文件服务器

### springboot整合mybatis

springboot + mybatis

### nginx文件服务器

- nginx.conf文件配置

```
user root;
worker_processes auto;
error_log /var/log/nginx/error.log;
pid /run/nginx.pid;

include /usr/share/nginx/modules/*.conf;

events {
    worker_connections 1024;
}

http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    tcp_nodelay         on;
    keepalive_timeout   65;
    types_hash_max_size 2048;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

    include /etc/nginx/conf.d/*.conf;

    server {
        listen       6868 default_server;
        listen       [::]:6868 default_server;
        server_name  _;
	root         /;
        location /files {
	    	alias /etc/nginx;
        	autoindex on;    #开启索引功能
        	autoindex_exact_size off;  #关闭计算文件确切大小（单位bytes），只显示大概大小（单位kb、mb、gb）
        	autoindex_localtime on;   #显示本机时间而非 GMT 时间
        }

        error_page 404 /404.html;
            location = /40x.html {
        }

        error_page 500 502 503 504 /50x.html;
            location = /50x.html {
        }
    }

}
```

- 服务器开启6868端口即可访问 http://你的服务器ip:6868/files 查看效果！