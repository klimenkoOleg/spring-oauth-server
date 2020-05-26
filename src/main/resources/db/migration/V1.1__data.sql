/* client_secret column is set as the encrypted value of "secret" - the password for the clients  */
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
('fooClientIdPassword', '$2a$10$F2dXfNuFjqezxIZp0ad5OeegW43cRdSiPgEtcetHspiNrUCi3iI6O', 'foo,read,write',
 'password,authorization_code,refresh_token,client_credentials', null, null, 36000, 36000, null, true);
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
('sampleClientId', '$2a$10$F2dXfNuFjqezxIZp0ad5OeegW43cRdSiPgEtcetHspiNrUCi3iI6O', 'read,write,foo,bar',
 'implicit', null, null, 36000, 36000, null, false);
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
('barClientIdPassword', '$2a$10$F2dXfNuFjqezxIZp0ad5OeegW43cRdSiPgEtcetHspiNrUCi3iI6O', 'bar,read,write',
 'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);

insert  into authority(name) values ('ADMIN');
-- insert  into users(account_expired,account_locked,credentials_expired,enabled,password,user_name) values (0,0,0,1,'admin','admin');
insert  into users(account_expired,account_locked,credentials_expired,enabled,password,user_name) values (0,0,0,1,'$2a$04$mRzzeBK/WBkzppne0IVoMOFP/WCvvbTLzmjHtGFLu52EFn28TNKVu','admin');
insert  into user_authority(authority_id,user_id) values (1,1);