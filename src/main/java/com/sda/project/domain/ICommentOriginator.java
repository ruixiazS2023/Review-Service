package com.sda.project.domain;


/**
 *
 * This interface will be implemented by Review class to achieve Memento
 * pattern.
 *
 */
public interface ICommentOriginator {
    CommentMemento createMemento();
    void restoreFromMemento(CommentMemento memento);
}
